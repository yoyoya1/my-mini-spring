package cn.py.springframework.beans.factory.support;

import cn.py.springframework.beans.BeansException;
import cn.py.springframework.beans.PropertyValue;
import cn.py.springframework.beans.PropertyValues;
import cn.py.springframework.beans.factory.config.BeanDefinition;
import cn.py.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanDefinition, bean, beanName);
        } catch (BeansException e) {
            throw new BeansException("Instantiation of bean failed");
        }
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * bean属性填充
     */
    protected void applyPropertyValues(BeanDefinition beanDefinition, Object bean, String beanName) {
        try {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        // 循环填充
        for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
//                value = getBean(name); 为什么要用下面这种设计?
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            // 设置bean值
            Field declaredField = beanDefinition.getBeanClass().getDeclaredField(name);
            declaredField.setAccessible(true);
            declaredField.set(bean, value);
        }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new BeansException("Failed to apply propertyValues");
        }
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return null;
    }

    /**
     * 拿到Bean的所有Constructor,和args一一比对,找到合适的constructor初始化Bean
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    private InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
    }


}
