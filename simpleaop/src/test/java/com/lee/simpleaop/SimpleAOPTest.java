package com.lee.simpleaop;

import org.junit.Test;

/**
 * @author WangLe
 * @date 2019/12/9 15:58
 * @description
 */
public class SimpleAOPTest {
    @Test
    public void getProxy() {
        // 1. 创建一个MethodInvocation实现类
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloServiceImpl helloService = new HelloServiceImpl();

        // 2. 创建一个 Advice
        Advice before = new BeforeAdvice(helloService, logTask);

        // 3. 为目标对象生成代理,核心方法,知道java动态代理的执行逻辑和原理也就了解了这些类是干嘛用的
        HelloService helloServiceProxy = (HelloService) SimpleAOP.getProxy(helloService, before);

        // 执行生成的代理类的sayHello()方法,其实就是在执行before对象的invoke方法
        helloServiceProxy.sayHello();

    }
}
