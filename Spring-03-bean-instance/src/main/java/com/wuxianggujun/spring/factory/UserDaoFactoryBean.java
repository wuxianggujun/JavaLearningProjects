package com.wuxianggujun.spring.factory;

import com.wuxianggujun.spring.dao.UserDao;
import com.wuxianggujun.spring.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.FactoryBean;

public class UserDaoFactoryBean implements FactoryBean<UserDao> {

    //����ԭʼʵ��������������ķ���
    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    //true �ǵ���ģʽ,bean����һ���ǵ���ģʽ
    //false �Ƿǵ���ģʽ
    @Override
    public boolean isSingleton() {
        return true;
    }

}
