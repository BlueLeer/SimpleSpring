package com.lee.toyspring.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author WangLe
 * @date 2019/12/11 15:15
 * @description
 */
final public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        super(advised);
    }

    /**
     * 生成代理对象
     *
     * @return bean的代理对象
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), advised.getTargetSource().getInterfaces(), this);
    }


    /**
     * 该方法是InvocationHandler接口的具体实现,包含了将通知(Advice)织入相关方法中
     * @param proxy
     * @param method
     * @param args
     * @return 代理方法或者原方法的返回值,如果MethodMatcher对象匹配上该方法了,就返回代理方法执行后的返回值,否则返回原方法执行后的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMatcher methodMatcher = advised.getMethodMatcher();
        if (methodMatcher != null && methodMatcher.matchers(method, advised.getTargetSource().getTargetClass())) {
            // 获取Advice. MethodInterceptor的父接口Interceptor继承了Advice接口
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();

            // 将 bean 的原生method封装成MethodInvocation实现类对象
            // 将生成的对象传给 Advise 实现类对象,执行通知逻辑
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
        }
        // 如果方法匹配器不匹配,则不执行通知逻辑,也就是只执行代理对象原来的方法
        else {
            return method.invoke(advised.getTargetSource().getTarget(), args);
        }
    }
}
