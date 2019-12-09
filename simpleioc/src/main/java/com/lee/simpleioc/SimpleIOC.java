package com.lee.simpleioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangLe
 * @date 2019/12/9 13:35
 * @description 实现一个简单的IOC容器, 最简单的IOC容器只需要4步即可实现, 如下:
 * 1.加载xml配置文件,遍历其中的标签
 * 2.获取标签中的id和class属性,加载class属性所对应的类,并创建bean
 * 3.遍历标签中的标签,获取属性值,并属性值填充到bean中
 * 4.将bean注册到bean容器中(容器使用Map,使用map装bean)
 */
public class SimpleIOC {
    // 盛装bean的容器
    private Map<String, Object> beanContainer = new HashMap<>();

    /**
     * IOC容器构建器
     *
     * @param configLocation 配置bean的配置文件
     */
    public SimpleIOC(String configLocation) throws Exception {
        loadBeans(configLocation);
    }

    /**
     * 根据ID获取bean实体
     *
     * @param id
     * @return
     */
    public Object getBean(String id) {
        if (id == null || id.length() == 0) {
            throw new IllegalArgumentException("id不能为空!");
        }

        Object o = beanContainer.get(id);
        if (o == null) {
            throw new IllegalArgumentException("当前ID的bean不存在,请检查ID是否正确!");
        }

        return o;
    }

    private void loadBeans(String configLocation) throws Exception {
        InputStream is = new FileInputStream(configLocation);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(is);

        // <beans>标签,根标签
        Element root = doc.getDocumentElement();
        // <bean>标签
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node bean = childNodes.item(i);
            boolean b = bean instanceof Element;
            if (!b) {
                throw new Exception("xml配置文件格式不对,解析失败!");
            }

            Element element = (Element) bean;
            String id = element.getAttribute("id");
            String className = element.getAttribute("class");

            // 加载beanClass
            Class beanClass = null;
            try {
                beanClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }

            // 创建bean
            Object instance = beanClass.newInstance();

            // 遍历property标签
            NodeList property = element.getElementsByTagName("property");
            for (int p = 0; p < property.getLength(); p++) {
                Node item = property.item(p);
                b = item instanceof Element;
                if (!b) {
                    throw new Exception("xml配置文件格式不对-property标签格式不对,解析失败!");
                }

                Element propertyNode = (Element) item;
                // bean的属性
                String field = propertyNode.getAttribute("name");
                // bean的属性值
                String value = propertyNode.getAttribute("value");

                // 利用反射将bean相关字段设置为可访问
                Field declaredField = instance.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);

                if (value != null && value.length() > 0) {
                    // 将属性值填充到bean的属性上
                    declaredField.set(instance, value);
                } else {
                    String refValue = propertyNode.getAttribute("ref");
                    if (refValue == null || refValue.length() == 0) {
                        throw new IllegalArgumentException("ref value error!");
                    }

                    // 将引用填充到bean的属性值上
                    declaredField.set(instance, getBean(refValue));
                }

            }

            // 将bean注册到容器上
            registerBean(id,instance);

        }

    }

    /**
     * 注册bean到容器中
     *
     * @param id
     * @param bean
     */
    private void registerBean(String id, Object bean) {
        beanContainer.put(id, bean);
    }
}
