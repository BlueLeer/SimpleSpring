package com.lee.toyspring.aop;

import com.lee.toyspring.component.HelloService;
import com.lee.toyspring.component.HelloServiceImpl;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class JdkDynamicAopProxyTest {

    @Test
    public void getProxy() {
        System.out.println("===== No Proxy =====");
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello();

        System.out.println("===== Proxy =====");
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new LogInterceptor());

        TargetSource targetSource = new TargetSource(helloService.getClass(), helloService.getClass().getInterfaces(), helloService);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher((method, beanClass) -> false);

        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        helloService = (HelloService) jdkDynamicAopProxy.getProxy();

        helloService.sayHello();
    }
}