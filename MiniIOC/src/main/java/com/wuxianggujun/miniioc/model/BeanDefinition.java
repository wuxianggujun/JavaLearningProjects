package com.wuxianggujun.miniioc.model;

/**
 * 对象定义属性
 */
public interface BeanDefinition {
    String getName();

    void setName(String name);

    String getClassName();

    void setClassName(String className);

    String getScope();

    void setScope(String scope);

    boolean isLazyInit(boolean lazyInit);

    void setInitializer(String initializer);

    String getInitializer();

    void setDestroy(String destroy);

    String getDestroy();

    void setFactoryMethod(String factoryMethod);

    String getFactoryMethod();
}
