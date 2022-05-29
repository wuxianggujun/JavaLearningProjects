package com.wuxianggujun.spring.service.impl;

import com.wuxianggujun.spring.dao.BookDao;
import com.wuxianggujun.spring.dao.UserDao;
import com.wuxianggujun.spring.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    private UserDao userDao;

    @Override
    public void save() {
        System.out.println("book service ....");
        bookDao.save();
        userDao.save();
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
