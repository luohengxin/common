package com.cn.common.mvc.http;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class ControllerBeanPostProcessor implements BeanPostProcessor {


    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Class<?> beanClass = bean.getClass();

        if (!isHandler(beanClass)) {
            return null;
        }
        MethodLog annotation = beanClass.getAnnotation(MethodLog.class);
        if (Objects.nonNull(annotation)) {
            return createProxy(beanClass, bean, Arrays.asList(beanClass.getMethods()));
        }

        Method[] methods = beanClass.getMethods();
        List<Method> needProxyMethods = new ArrayList<>();
        for (Method method : methods) {
            MethodLog annotation1 = method.getAnnotation(MethodLog.class);
            if (Objects.nonNull(annotation1)) {
                needProxyMethods.add(method);
            }
        }
        if (needProxyMethods.size() > 0) {
            return createProxy(beanClass, bean, needProxyMethods);
        }
        return null;
    }


    private boolean isHandler(Class<?> beanType) {
        return (AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) ||
                AnnotatedElementUtils.hasAnnotation(beanType, RequestMapping.class));
    }


    private Object createProxy(Class<?> beanClass, Object bean, List<Method> methods) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallbacks(new MethodInterceptor[]{new DefaultInterceptor(bean), new ControllerMethodInterceptor(bean)});
        enhancer.setCallbackFilter(new ControllerMethodFilter(methods));
        return enhancer.create();
    }

    public class ControllerMethodInterceptor implements MethodInterceptor {

        private Object target;

        public ControllerMethodInterceptor(Object target) {
            this.target = target;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            MethodLog methodAnnotation = method.getAnnotation(MethodLog.class);
            boolean before;
            boolean after;
            if (Objects.nonNull(methodAnnotation)) {
                before = methodAnnotation.before();
                after = methodAnnotation.after();
            } else {
                MethodLog typeAnnotation = method.getDeclaringClass().getAnnotation(MethodLog.class);
                before = typeAnnotation.before();
                after = typeAnnotation.after();
            }

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            //读取session中的用户
            //User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
            String remoteAddr = request.getRemoteAddr();
            String requestURI = request.getRequestURI();

            if (before) {
                // 按照要求记录对应日志
                System.out.println("before");
            }
            Object result = method.invoke(target, objects);
            if (after) {
                System.out.println("after");
            }
            return result;
        }
    }

    public class DefaultInterceptor implements MethodInterceptor {
        private Object target;

        public DefaultInterceptor(Object target) {
            this.target = target;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return method.invoke(target, objects);

        }
    }

    public class ControllerMethodFilter implements CallbackFilter {


        private List<Method> methods;

        public ControllerMethodFilter(List<Method> methods) {
            this.methods = methods;
        }

        @Override
        public int accept(Method method) {
            return methods.contains(method) ? 1 : 0;
        }
    }

}
