package com.wuxianggujun.spring_ioc_simple;

import com.wuxianggujun.spring_ioc_simple.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForName {
    public static void main(String[] args) {
        //��ȡIOC����
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        /*
        ��ȡbean
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
        */
        BookService bookService = (BookService) ctx.getBean("service");
        bookService.save();
    }
}
