package com.lee.simpleaop;

import java.lang.reflect.Proxy;

/**
 * @author WangLe
 * @date 2019/12/9 15:43
 * @description
 */
public class SimpleAOP {
    public static Object getProxy(Object bean,Advice advice) {
        // newProxyInstance()方法是Java提供的生成动态代理对象的方法,一共有三个参数:
        // 1. 类加载器
        // 2. 需要被代理的接口,数组  (被代理的目标)
        // 3. 代理对象,主要是其中的invoke()方法,应该是一个InvocationHandler对象
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),bean.getClass().getInterfaces(),advice);
    }
}
