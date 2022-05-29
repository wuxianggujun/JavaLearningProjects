package com.wuxianggujun.spring;

import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForLifeCycle {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //ctx.registerShutdownHook();//等到容器内部的程序结束关闭容器
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
        //ctx.close(); //暴力关闭直接关闭

    }
}
