package com.wuxianggujun.spring_ioc_simple;

import com.wuxianggujun.spring_ioc_simple.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForScope {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        System.out.println(bookDao);
        BookDao bookDao2 = (BookDao) ctx.getBean("bookDao");
        System.out.println(bookDao2);
    }
}
