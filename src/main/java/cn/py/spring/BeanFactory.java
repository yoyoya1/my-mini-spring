package cn.py.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {
    private Map<String, BeanDefinition> beanFactory = new ConcurrentHashMap<>();

    public BeanDefinition getBeanDefinition(String beanName) {
        return beanFactory.get(beanName);
    }

    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanFactory.put(beanName, beanDefinition);
    }
}
