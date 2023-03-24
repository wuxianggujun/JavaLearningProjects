package com.wuxianggujun.miniioc.bean;

public interface BeanFactory {
    /**
     * 根据名称获取对应的实例信息
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

    <T> T getBean(String name, Class<T> requiredType);

    boolean containsBean(String beanName);

    boolean isTypeMatch(String beanName, Class requiredType);

    Class<?> getType(String beanName);

}
