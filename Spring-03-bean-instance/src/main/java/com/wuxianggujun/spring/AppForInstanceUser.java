package com.wuxianggujun.spring;

import com.wuxianggujun.spring.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForInstanceUser {
    public static void main(String[] args) {
        /*
         //����ʵ����������
         UserDaoFactory userDaoFactory = new UserDaoFactory();
         //ͨ��ʵ���������󴴽�����
         UserDao userDao = userDaoFactory.getUserDao();
         userDao.save();
        */

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) ctx.getBean("userDao");
        userDao.save();
    }
}
