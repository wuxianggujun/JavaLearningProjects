package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;

public class BookDaoImpl implements BookDao {

    public BookDaoImpl(String databaseName, int connectionNum) {
        this.databaseName = databaseName;
        this.connectionNum = connectionNum;
    }

    private String databaseName;
    private int connectionNum;

    @Override
    public void save() {
        System.out.println("Book dao save...."+databaseName+","+connectionNum);
    }
}
