package com.lee.simpleioc;


import com.lee.simpleioc.component.Car;
import com.lee.simpleioc.component.Wheel;
import org.junit.Test;

import java.net.URL;

public class SimpleIOCTest {
    @Test
    public void test() {
        String configPath = SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();

        SimpleIOC simpleIOC = null;
        try {
            simpleIOC = new SimpleIOC(configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Car benzCar = (Car) simpleIOC.getBean("benzCar");
        Wheel benzWheel = (Wheel) simpleIOC.getBean("benzWheel");

        System.out.println(benzCar);
        System.out.println(benzWheel);

    }
}