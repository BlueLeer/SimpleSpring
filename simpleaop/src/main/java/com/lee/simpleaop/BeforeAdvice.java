package com.lee.simpleaop;

import java.lang.reflect.Method;

/**
 * @author WangLe
 * @date 2019/12/9 15:48
 * @description
 */
public class BeforeAdvice implements Advice {

    /**
     * 代理的对象
     */
    private Object bean;

    /**
     * 相当于"通知"部分所要执行的代码逻辑
     */
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标方法之前执行通知
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }
}
