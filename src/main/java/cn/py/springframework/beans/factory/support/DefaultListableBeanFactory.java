package cn.py.springframework.beans.factory.support;

import cn.py.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{
    Map<String, BeanDefinition> BeanDefinitionMap = new ConcurrentHashMap<>();
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        return BeanDefinitionMap.get(beanName);
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        BeanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return beanDefinition != null;
    }

}
