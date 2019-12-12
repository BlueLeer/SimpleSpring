package com.lee.toyspring.aop;

/**
 * @author WangLe
 * @date 2019/12/12 11:14
 * @description 切点
 */
public interface PointCut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
