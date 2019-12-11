package com.lee.toyspring.ioc.xml;

import com.lee.toyspring.ioc.BeanDefinition;
import com.lee.toyspring.ioc.BeanDefinitionReader;
import com.lee.toyspring.ioc.BeanReference;
import com.lee.toyspring.ioc.PropertyValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取配置文件
 *
 * @author WangLe
 * @date 2019/12/11 10:08
 * @description
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {
    /**
     * 用于存放解析到的BeanDefinition对象,key是beanId
     */
    private Map<String, BeanDefinition> registry;

    public XmlBeanDefinitionReader() {
        this.registry = new HashMap<>();
    }


    @Override
    public void loadBeanDefinitions(String configPath) throws FileNotFoundException, Exception {
        InputStream is = new FileInputStream(configPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document document = docBuilder.parse(is);
        parseBeanDefinitions(document);
    }

    private void parseBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        NodeList beanNodes = root.getChildNodes();

        for (int i = 0; i < beanNodes.getLength(); i++) {
            Node node = beanNodes.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                parseBeanDefinition(element);
            }
        }
    }

    /**
     * 解析单个<bean>标签
     *
     * @param element
     */
    private void parseBeanDefinition(Element element) {
        String id = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        processProperty(element, beanDefinition);
        registry.put(id, beanDefinition);
    }

    /**
     * 获取bean标签内的property标签里面的内容
     *
     * @param element
     * @param beanDefinition
     */
    private void processProperty(Element element, BeanDefinition beanDefinition) {
        NodeList properties = element.getElementsByTagName("property");
        for (int i = 0; i < properties.getLength(); i++) {
            Node property = properties.item(i);
            if (property instanceof Element) {
                Element propertyElement = (Element) property;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref config error");
                    }

                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));

                }
            }
        }
    }

    @Override
    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }
}
