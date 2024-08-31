package cn.py.spring;

import cn.py.spring.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        BeanFactory beanFactory = new BeanFactory();

        beanFactory.registryBeanDefinition("userService", new BeanDefinition(new UserService("py", "handSome man")));

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");

        UserService instance = (UserService) beanDefinition.getInstance();

        System.out.println(instance.getUserName() + " " + instance.getUserInfo());
    }
}
