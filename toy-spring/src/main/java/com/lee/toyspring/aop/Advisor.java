package com.lee.toyspring.aop;

import org.aopalliance.aop.Advice;

/**
 * @author WangLe
 * @date 2019/12/12 11:13
 * @description
 */
public interface Advisor {
    /**
     * 获取通知
     *
     * @return
     */
    Advice getAdvice();
}
