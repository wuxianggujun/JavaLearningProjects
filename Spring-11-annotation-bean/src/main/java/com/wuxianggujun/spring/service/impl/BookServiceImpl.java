package com.wuxianggujun.spring.service.impl;

import com.wuxianggujun.spring.dao.BookDao;
import com.wuxianggujun.spring.service.BookService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component("bookService")
@Service
public class BookServiceImpl implements BookService{
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        System.out.println("book service save....");
        bookDao.save();
    }

}
