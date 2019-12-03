package com.cn.common.util.aop;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;

public class HelloWorld
{

    public void sayHello(){


        System.out.println("hello world!");
    }


    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new HelloWorld());

        // 1.分别 添加前置增强 、后置增强
        // proxyFactory.addAdvice(new HelloBeforeAdvice());
        // proxyFactory.addAdvice(new HelloAfterAdvice());

        // 2.同时 实现前置增强、后置增强
        // proxyFactory.addAdvice(new HelloBeforeAfterAdvice());

        // 3.环绕增强
        // proxyFactory.addAdvice(new HelloAroundAdvice());
        // proxyFactory.addAdvice(new HelloThrowAdvice());
        HelloWorld hello = (HelloWorld) proxyFactory.getProxy();
        Advised advised = (Advised) hello;
        advised.addAdvice(new HelloBeforeAdvice());
        advised.addAdvice(new HelloAfterAdvice());
        System.out.println(hello instanceof Advised);

        hello.sayHello();

    }

}
