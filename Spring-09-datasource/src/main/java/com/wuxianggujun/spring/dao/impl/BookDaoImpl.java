package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;

public class BookDaoImpl implements BookDao {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void save() {
        System.out.println("BookDao save..."+name);
    }
}
