package com.lee.toyspring.aop;

/**
 * @author WangLe
 * @date 2019/12/11 15:09
 * @description
 */
public class TargetSource {
    /**
     * 被代理的对象的类
     */
    private Class<?> targetClass;
    /**
     * 被代理的对象实现的接口
     */
    private Class<?>[] interfaces;
    /**
     * 被代理的对象
     */
    private Object target;

    /**
     *
     * @param targetClass 被代理的对象的类
     * @param interfaces 被代理的对象实现的接口
     * @param target 被代理的对象
     */
    public TargetSource(Class<?> targetClass, Class<?>[] interfaces, Object target) {
        this.targetClass = targetClass;
        this.interfaces = interfaces;
        this.target = target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public Object getTarget() {
        return target;
    }
}
