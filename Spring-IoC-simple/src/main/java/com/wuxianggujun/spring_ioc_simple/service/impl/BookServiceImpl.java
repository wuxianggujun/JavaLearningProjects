package com.wuxianggujun.spring_ioc_simple.service.impl;

import com.wuxianggujun.spring_ioc_simple.dao.BookDao;
import com.wuxianggujun.spring_ioc_simple.service.BookService;

public class BookServiceImpl implements BookService {

    //删除业务层中使用new的方式创建的dao对象
    private BookDao bookDao;

    @Override
    public void save() {
        System.out.println("Book Service save...");
        bookDao.save();
    }

    //提供对应的set方法
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

}
