package com.lee.toyspring.ioc.factory;

/**
 * @author WangLe
 * @date 2019/12/11 9:46
 * @description
 * BeanFactory的生命流程:
 * 1.BeanFactory加载Bean的配置文件,将读到的Bean配置封装成BeanDefinition对象
 * 2.将封装好的BeanDefinition对象注册到BeanDefinition容器中
 * 3.注册BeanPostProcessor相关实现类到BeanPostProcessor容器中
 * 4.BeanFactory进入到就绪状态
 * 5.外部调用BeanFactory的getBean()方法,BeanFactory着手实例化响应的Bean(在这里使用的是Bean的懒加载)
 *
 */
public interface BeanFactory {
    /**
     * 通过id获取bean(完成对bean的实例化)
     * @param beanName xml配置文件中的bean的id
     * @return
     * @throws Exception
     */
    Object getBean(String beanName) throws Exception;
}
