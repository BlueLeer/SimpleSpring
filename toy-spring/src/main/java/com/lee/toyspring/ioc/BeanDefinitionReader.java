package com.lee.toyspring.ioc;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author WangLe
 * @date 2019/12/11 10:12
 * @description
 */
public interface BeanDefinitionReader {
    /**
     * 加载ioc容器的配置文件,并解析配置文件
     *
     * @param configPath 配置文件的路径
     * @throws FileNotFoundException
     * @throws Exception
     */
    void loadBeanDefinitions(String configPath) throws FileNotFoundException, Exception;

    Map<String, BeanDefinition> getRegistry();
}
