package cn.py.springframework.beans.factory.support;

import cn.py.springframework.beans.BeansException;
import cn.py.springframework.beans.factory.BeanFactory;
import cn.py.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        // 从单例Bean工厂中拿到对应的单例Bean
        Object singletonBean = getSingleton(beanName);
        if (singletonBean != null) {
            return singletonBean;
        }
        // 单例Bean工厂中不存在Bean, 根据BeanDefinition创建
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    @Override
    public Object getBean(String beanName) {
        return getBean(beanName, (Object[]) null);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
