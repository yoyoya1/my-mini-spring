package cn.py.springframework.beans.factory.support;

import cn.py.springframework.beans.BeansException;
import cn.py.springframework.core.io.Resource;
import cn.py.springframework.core.io.ResourceLoader;

/**
 * 读取Resource资源, 注册BeanDefinition
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinitions(Resource resource) throws BeansException;
    void loadBeanDefinitions(Resource ...resources) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;
}
