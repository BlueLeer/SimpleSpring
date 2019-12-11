package com.lee.toyspring.ioc.factory;

/**
 * @author WangLe
 * @date 2019/12/11 9:47
 * @description
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory factory) throws Exception;
}
