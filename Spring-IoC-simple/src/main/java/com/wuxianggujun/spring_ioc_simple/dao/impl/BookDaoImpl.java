package com.wuxianggujun.spring_ioc_simple.dao.impl;

import com.wuxianggujun.spring_ioc_simple.dao.BookDao;

public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("Œ“ «"+BookDaoImpl.class.getSimpleName());
    }
}
