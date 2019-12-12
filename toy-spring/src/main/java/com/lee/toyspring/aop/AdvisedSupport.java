package com.lee.toyspring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 通知支持辅助类
 *
 * @author WangLe
 * @date 2019/12/11 15:08
 * @description
 * 包含了构建动态代理所需要的对象:
 * 1.被代理的目标
 * 2.
 */
public class AdvisedSupport {
    /**
     * 被代理对象的一些信息(包括其对象,类,实现的接口)
     */
    private TargetSource targetSource;

    /**
     * 通知
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
