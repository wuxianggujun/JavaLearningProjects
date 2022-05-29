package com.wuxianggujun.spring.factory;

import com.wuxianggujun.spring.dao.UserDao;
import com.wuxianggujun.spring.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.FactoryBean;

public class UserDaoFactoryBean implements FactoryBean<UserDao> {

    //代替原始实例工厂创建对象的方法
    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    //true 是单利模式,bean创建一般是单例模式
    //false 是非单利模式
    @Override
    public boolean isSingleton() {
        return true;
    }

}
