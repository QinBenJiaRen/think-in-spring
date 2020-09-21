package org.haiyang.www.demo;

import org.haiyang.www.bean.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

public class BeanDefinitionDemo {

    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "haiyang");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

    }
}
