package com.lee.simpleaop;

import org.junit.Test;

/**
 * @author WangLe
 * @date 2019/12/9 16:48
 * @description
 */
public class SimpleAOPTest2 {
    @Test
    public void test() {
        // 1.构建MethodInvocation的实现类
        MethodInvocation methodInvocation = () -> System.out.println("log task end");

        HelloServiceImpl helloService = new HelloServiceImpl();

        // 2. 构建Advice,该类中方法invoke方法是接口被代理以后实际上要执行的方法
        Advice afterAdvice = new AfterAdvice(helloService, methodInvocation);

        // 3.生成代理对象
        HelloService proxy = (HelloService) SimpleAOP.getProxy(helloService, afterAdvice);

        proxy.sayHello();

    }
}
