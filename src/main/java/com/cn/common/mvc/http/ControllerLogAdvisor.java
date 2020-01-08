package com.cn.common.mvc.http;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Objects;

public class ControllerLogAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    public ControllerLogAdvisor() {
        super.setAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {

                Method method = invocation.getMethod();
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
                Object result = invocation.proceed();
                if (after) {
                    System.out.println("after");
                }

                Object proceed = invocation.proceed();
                return proceed;
            }
        });

    }

    private ControllerLogPointcut pointcut = new ControllerLogPointcut();

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public class ControllerLogPointcut extends StaticMethodMatcherPointcut {
        @Override
        public boolean matches(Method method, @Nullable Class<?> targetClass) {
            Controller mergedAnnotation = AnnotatedElementUtils.findMergedAnnotation(targetClass, Controller.class);
            if (Objects.isNull(mergedAnnotation)) {
                return false;
            }
            if (method.isAnnotationPresent(MethodLog.class) || targetClass.isAnnotationPresent(MethodLog.class)) {
                return true;
            }
            return false;
        }
    }


}
