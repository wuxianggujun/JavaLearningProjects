package com.wuxianggujun.spring.factory;

import com.wuxianggujun.spring.dao.UserDao;
import com.wuxianggujun.spring.dao.impl.UserDaoImpl;

public class UserDaoFactory {
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
