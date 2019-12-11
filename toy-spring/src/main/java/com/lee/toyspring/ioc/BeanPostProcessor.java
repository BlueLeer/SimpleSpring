package com.lee.toyspring.ioc;

/**
 * 类似于Spring中的BeanPostProcessor,其主要的用途是提供一个机会,让开发人员能够着手bean的实例化过程.通过实现这个接口,我们可以在bean的实例化的时,对bean进行一些处理。
 * 比如我们熟悉的AOP就是在这里将切面逻辑织入相关的bean中的.正是因为有了BeanPostProcessor接口作为桥梁,才使得IOC和AOP产生了关联
 *
 * @author WangLe
 * @date 2019/12/11 13:21
 * @description
 */
public interface BeanPostProcessor {
    /**
     * 在bean实例化之前执行的逻辑
     *
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;


    /**
     * 在bean实例化之后执行的逻辑
     *
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
