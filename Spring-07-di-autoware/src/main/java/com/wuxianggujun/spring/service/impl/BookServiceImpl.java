package com.wuxianggujun.spring.service.impl;

import com.wuxianggujun.spring.dao.BookDao;
import com.wuxianggujun.spring.dao.UserDao;
import com.wuxianggujun.spring.service.BookService;

public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    @Override
    public void save() {
        System.out.println("Book Service save ...");
        bookDao.save();
    }


    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
