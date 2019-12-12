package com.lee.toyspring.aop;

/**
 * @author WangLe
 * @date 2019/12/11 15:03
 * @description
 */
public abstract class AbstractAopProxy implements AopProxy {
    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
