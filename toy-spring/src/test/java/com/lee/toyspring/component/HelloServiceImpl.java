package com.lee.toyspring.component;

/**
 * @author WangLe
 * @date 2019/12/12 10:02
 * @description
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}
