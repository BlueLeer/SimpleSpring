package com.lee.toyspring.ioc;

/**
 * @author WangLe
 * @date 2019/12/11 10:00
 * @description
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
