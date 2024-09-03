package cn.py.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.py.springframework.beans.BeansException;
import cn.py.springframework.beans.PropertyValue;
import cn.py.springframework.beans.factory.config.BeanDefinition;
import cn.py.springframework.beans.factory.config.BeanReference;
import cn.py.springframework.core.io.Resource;
import cn.py.springframework.core.io.ResourceLoader;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    /**
     * @param resource 资源(classPath、fileSystem、url)
     * @throws BeansException 异常
     */
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try(InputStream is = resource.getInputStream()) {
            doLoadBeanDefinitions(is);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource + e);
        }
    }

    /**
     * 对相应的流做XML文件解析
     * @param is 流
     * @throws ClassNotFoundException 类无法找到异常
     */
    private void doLoadBeanDefinitions(InputStream is) throws ClassNotFoundException{
        // 拿到整个xml文件doc
        Document doc = XmlUtil.readXML(is);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i ++ ) {
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)) continue;
            // 判断bean
            if (!"bean".equals(childNodes.item(i).getNodeName())) continue;

            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String classPath = bean.getAttribute("class");

            Class<?> clazz = Class.forName(classPath);
            // id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmptyIfStr(beanName)) {
                beanName = clazz.getSimpleName();
            }
            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                // 解析标签：property
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registryBeanDefinition(beanName, beanDefinition);
        }
    }

    /**
     * @param resources 多个resource
     * @throws BeansException 异常
     */
    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    /**
     * resourceLoader根据location加载对应的Resource
     * @param location 路径
     * @throws BeansException 异常
     */
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }
}
