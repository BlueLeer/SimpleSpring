package com.lee.simpleaop;

import java.lang.reflect.Proxy;

/**
 * @author WangLe
 * @date 2019/12/9 15:43
 * @description
 */
public class SimpleAOP {
    public static Object getProxy(Object bean,Advice advice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),bean.getClass().getInterfaces(),advice);
    }
}
