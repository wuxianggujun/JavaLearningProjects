package com.wuxianggujun.spring.service.impl;

import com.wuxianggujun.spring.dao.BookDao;
import com.wuxianggujun.spring.dao.UserDao;
import com.wuxianggujun.spring.service.BookService;

public class BookServiceImpl implements BookService {


    public BookServiceImpl(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    private BookDao bookDao;
    private UserDao userDao;

    @Override
    public void save() {
        System.out.println("Book Service save ...");
        bookDao.save();
        userDao.save();
    }


}
