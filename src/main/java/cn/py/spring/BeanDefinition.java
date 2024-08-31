package cn.py.spring;

public class BeanDefinition {
    private Object bean;

    BeanDefinition(Object obj) {
        this.bean = obj;
    }

    public Object getInstance() {
        return this.bean;
    }
}
