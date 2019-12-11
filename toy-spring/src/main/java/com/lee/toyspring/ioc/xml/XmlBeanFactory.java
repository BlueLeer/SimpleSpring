package com.lee.toyspring.ioc.xml;

import com.lee.toyspring.ioc.*;
import com.lee.toyspring.ioc.factory.BeanFactory;
import com.lee.toyspring.ioc.factory.BeanFactoryAware;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author WangLe
 * @date 2019/12/11 9:50
 * @description
 */
public class XmlBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private List<String> beanDefinitionNames = new ArrayList<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private BeanDefinitionReader beanDefinitionReader;

    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("no this bean with name " + beanName);
        }

        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = createBean(beanDefinition);
            bean = processBean(bean, beanName);
            // 将已经经历过实例化,并且初始化好了的bean放入BeanDefinition对象当中,以供下次使用
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    private Object processBean(Object bean, String beanName) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
        }

        return bean;
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        Object instance = beanDefinition.getBeanClass().newInstance();
        applyBeanProperties(instance, beanDefinition);

        return instance;
    }

    private void applyBeanProperties(Object instance, BeanDefinition beanDefinition) throws Exception {
        if (instance instanceof BeanFactoryAware) {
            ((BeanFactoryAware) instance).setBeanFactory(this);
        }

        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues().getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            Object value = propertyValue.getValue();

            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            // 利用反射,优先利用setter设置实例的属性,如果通过setter设置属性失败,则再通过获取实例的field进行赋值
            try {
                String setterName = "set" + propertyValue.getName().substring(0, 1).toUpperCase() + propertyValue.getName().substring(1);
                Method setterMethod = instance.getClass().getDeclaredMethod(setterName, value.getClass());
                // 设置访问权限为可访问
                setterMethod.setAccessible(true);

                setterMethod.invoke(instance, value);
            } catch (NoSuchMethodException e) {
                Field field = instance.getClass().getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                field.set(instance, value);
            }
        }
    }

    public XmlBeanFactory(String configPath) throws Exception {
        this.beanDefinitionReader = new XmlBeanDefinitionReader();
        loadBeanDefinitions(configPath);
    }

    private void loadBeanDefinitions(String configPath) throws Exception {
        beanDefinitionReader.loadBeanDefinitions(configPath);
        registerBeanDefinition();
        registerBeanPostProcessor();
    }

    private void registerBeanPostProcessor() {

    }

    private List getBeanForType(Class type) throws Exception {
        List beans = new ArrayList();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }

        return beans;
    }

    private void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    private void registerBeanDefinition() {
        Map<String, BeanDefinition> registry = beanDefinitionReader.getRegistry();
        // 将beanDefinitionReader中读取到的配置放入当前的beanDefinitionMap中
        // 这里为什么要一个个的取,而不是直接将beanDefinitionReader中的registry直接赋值给当前的beanDefinitionMap中呢? 因为我们可以在这里加入其他的逻辑
        Set<Map.Entry<String, BeanDefinition>> entries = registry.entrySet();
        for (Map.Entry<String, BeanDefinition> entry : entries) {
            String name = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            // TODO: 检查是否有重复的实例
            this.beanDefinitionMap.put(name, beanDefinition);
            this.beanDefinitionNames.add(name);
        }
    }
}
