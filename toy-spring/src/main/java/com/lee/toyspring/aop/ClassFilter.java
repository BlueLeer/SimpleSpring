package com.lee.toyspring.aop;

/**
 * @author WangLe
 * @date 2019/12/12 11:14
 * @description 类过滤器
 */
public interface ClassFilter {
    /**
     * 类是否匹配
     * @param beanClass
     * @return
     * @throws Exception
     */
    Boolean match(Class beanClass) throws Exception;
}
