package com.wuxianggujun.spring_ioc_simple.service.impl;

import com.wuxianggujun.spring_ioc_simple.dao.BookDao;
import com.wuxianggujun.spring_ioc_simple.dao.impl.BookDaoImpl;
import com.wuxianggujun.spring_ioc_simple.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void save() {
        bookDao.save();
    }
}
