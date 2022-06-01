package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;

public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("BookDao save...");
    }
}
