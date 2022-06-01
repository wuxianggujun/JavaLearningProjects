package com.wuxianggujun.spring;

import com.wuxianggujun.spring.dao.BookDao;
import com.wuxianggujun.spring.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
        BookService bookService = ctx.getBean(BookService.class);
        bookService.save();

    }
}
