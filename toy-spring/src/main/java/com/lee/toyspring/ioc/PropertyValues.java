package com.lee.toyspring.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangLe
 * @date 2019/12/11 10:00
 * @description
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        // 在这里可以对参数pv做一些处理
        this.propertyValues.add(pv);
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }
}
