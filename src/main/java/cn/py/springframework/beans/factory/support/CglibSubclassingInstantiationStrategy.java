package cn.py.springframework.beans.factory.support;

import cn.py.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{

    /**
     * @param beanDefinition bean定义类型
     * @param beanName       bean名称
     * @param ctor           bean对象初始化所需构造函数
     * @param args           bean对象构造函数所需参数
     * @return Bean实例化对象
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object... args) {
        return null;
    }
}
