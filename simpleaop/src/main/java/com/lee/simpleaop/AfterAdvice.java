package com.lee.simpleaop;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author WangLe
 * @date 2019/12/9 16:44
 * @description
 */
public class AfterAdvice implements Advice {
    private Object bean;
    private MethodInvocation methodInvocation;

    public AfterAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(this.bean, args);
        // 在目标方法之后执行
        this.methodInvocation.invoke();
        return invoke;
    }
}
