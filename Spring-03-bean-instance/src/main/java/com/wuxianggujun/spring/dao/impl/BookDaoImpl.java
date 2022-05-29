package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;

public class BookDaoImpl implements BookDao {

    private BookDaoImpl() {
        System.out.println("book dao constructor is running ......");
    }

    @Override
    public void save() {
        System.out.println("book dao save...");
    }
}
