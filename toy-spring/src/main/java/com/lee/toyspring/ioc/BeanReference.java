package com.lee.toyspring.ioc;

/**
 * @author WangLe
 * @date 2019/12/11 10:48
 * @description
 */
public class BeanReference {
    private String name;
    private Object bean;

    public BeanReference() {
    }

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
