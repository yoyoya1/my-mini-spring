package cn.py.springframework;

import cn.py.springframework.beans.*;
import cn.py.springframework.beans.factory.config.BeanDefinition;
import cn.py.springframework.beans.factory.config.BeanReference;
import cn.py.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.py.springframework.beans.factory.support.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}
