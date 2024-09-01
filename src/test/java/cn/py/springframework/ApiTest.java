package cn.py.springframework;

import cn.py.springframework.beans.BeansException;
import cn.py.springframework.beans.UserService;
import cn.py.springframework.beans.factory.config.BeanDefinition;
import cn.py.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "66");
        userService.queryUserInfo();
    }

}
