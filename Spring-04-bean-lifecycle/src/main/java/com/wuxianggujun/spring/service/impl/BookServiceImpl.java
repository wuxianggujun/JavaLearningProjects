package com.wuxianggujun.spring.service.impl;

import com.wuxianggujun.spring.dao.BookDao;
import com.wuxianggujun.spring.service.BookService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BookServiceImpl implements BookService, InitializingBean, DisposableBean {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        System.out.println("set ....");
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        bookDao.save();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("service init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("service destroy");
    }

}
