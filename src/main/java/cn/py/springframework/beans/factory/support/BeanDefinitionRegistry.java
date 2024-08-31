package cn.py.springframework.beans.factory.support;

import cn.py.springframework.beans.factory.BeanFactory;
import cn.py.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
