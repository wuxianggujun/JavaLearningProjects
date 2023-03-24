package com.wuxianggujun.miniioc.bean.impl;

import com.wuxianggujun.miniioc.bean.BeanFactory;
import com.wuxianggujun.miniioc.model.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory {
    /**
     * 对象信息容器
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 对象Map
     */
    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    protected void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public Object getBean(String beanName) {

        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }

    @Override
    public boolean containsBean(String beanName) {
        return false;
    }

    @Override
    public boolean isTypeMatch(String beanName, Class requiredType) {
        return false;
    }

    @Override
    public Class<?> getType(String beanName) {
        return null;
    }
}
