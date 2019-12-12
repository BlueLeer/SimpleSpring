package com.lee.toyspring.ioc.xml;

import com.lee.toyspring.component.Car;
import com.lee.toyspring.component.Wheel;
import org.junit.Test;

public class XmlBeanFactoryTest {

    @Test
    public void getBean() throws Exception {
        System.out.println("=================IOC TEST=================");
        String configPath = XmlBeanFactory.class.getClassLoader().getResource("ioc.xml").getPath();
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(configPath);
        System.out.println("XmlBeanFactory已经准备就绪...");
        Wheel benzWheel = (Wheel) xmlBeanFactory.getBean("benzWheel");
        Car benzCar = (Car) xmlBeanFactory.getBean("benzCar");
        System.out.println(benzWheel);
        System.out.println(benzCar);
    }
}