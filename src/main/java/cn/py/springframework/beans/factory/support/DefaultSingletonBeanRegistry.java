package cn.py.springframework.beans.factory.support;

import cn.py.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonBeanFactory = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonBeanFactory.get(beanName);
    }

    public void addSingleton(String beanName, Object singletonObject) {
        singletonBeanFactory.put(beanName, singletonObject);
    }
}
