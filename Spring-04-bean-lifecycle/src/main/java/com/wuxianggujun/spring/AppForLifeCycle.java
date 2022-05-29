package com.wuxianggujun.spring;

import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForLifeCycle {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //ctx.registerShutdownHook();//�ȵ������ڲ��ĳ�������ر�����
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
        //ctx.close(); //�����ر�ֱ�ӹر�

    }
}
