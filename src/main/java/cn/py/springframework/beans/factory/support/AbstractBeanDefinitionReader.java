package cn.py.springframework.beans.factory.support;

import cn.py.springframework.beans.BeansException;
import cn.py.springframework.core.io.DefaultResourceLoader;
import cn.py.springframework.core.io.Resource;
import cn.py.springframework.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = new DefaultResourceLoader();
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = resourceLoader;
    }

    /**
     * @return BeanDefinitionRegistry
     */
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.beanDefinitionRegistry;
    }

    /**
     * @return ResourceLoader
     */
    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
