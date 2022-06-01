package com.wuxianggujun.spring;

import com.wuxianggujun.spring.config.SpringConfig;
import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = ctx.getBean(BookDao.class);
        BookDao bookDao2 = ctx.getBean(BookDao.class);
        System.out.println(bookDao);
        System.out.println(bookDao2);
        ctx.close();

    }
}
