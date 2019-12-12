package com.lee.toyspring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author WangLe
 * @date 2019/12/12 10:06
 * @description
 */
public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(invocation.getMethod().getName() + " method start");
        // 执行被代理的方法
        Object proceed = invocation.proceed();
        System.out.println(invocation.getMethod().getName() + " method end");

        return proceed;
    }
}
