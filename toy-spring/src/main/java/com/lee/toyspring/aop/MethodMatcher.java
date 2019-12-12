package com.lee.toyspring.aop;

import java.lang.reflect.Method;

/**
 * @author WangLe
 * @date 2019/12/11 15:13
 * @description
 */
public interface MethodMatcher {
    Boolean matchers(Method method, Class beanClass);
}
