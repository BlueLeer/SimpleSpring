package com.lee.toyspring.aop;

import com.lee.toyspring.ioc.BeanPostProcessor;
import com.lee.toyspring.ioc.factory.BeanFactory;
import com.lee.toyspring.ioc.factory.BeanFactoryAware;
import com.lee.toyspring.ioc.xml.XmlBeanFactory;

/**
 * 该类是的AOP和IOC产生了联系,
 * BeanFactory在注册BeanPostProcessor接口相关实现类的阶段,会将其本身注入到AspectJAwareAdvisorAutoProxyCreator(下面简称 AutoProxyCreator)中,
 * 为后面AOP给bean生成代理对象做准备,BeanFactory 初始化结束后，AOP 与 IOC 桥梁类 AutoProxyCreator 也完成了实例化，并被缓存在 BeanFactory 中，
 * 静待 BeanFactory 实例化 bean。当外部产生调用，BeanFactory 开始实例化 bean 时。AutoProxyCreator 就开始悄悄的工作了，工作细节如下：
 *
 * @author WangLe
 * @date 2019/12/12 10:51
 * @description
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {
    private XmlBeanFactory xmlBeanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory factory) throws Exception {
        if (!(factory instanceof XmlBeanFactory)) {
            throw new IllegalArgumentException("Illegal bean factory!");
        }
        xmlBeanFactory = (XmlBeanFactory) factory;
    }
}
