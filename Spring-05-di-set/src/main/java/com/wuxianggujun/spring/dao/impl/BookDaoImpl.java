package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;

public class BookDaoImpl implements BookDao {

    private int connectionNumber;
    private String databaseName;

    @Override
    public void save() {
        System.out.println("book dao save ....." + databaseName + "," + connectionNumber);
    }

    public void setConnectionNumber(int connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
