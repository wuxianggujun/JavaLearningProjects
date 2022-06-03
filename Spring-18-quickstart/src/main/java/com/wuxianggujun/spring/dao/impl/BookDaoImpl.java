package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println(System.currentTimeMillis());
        System.out.println("book dao save...");
    }

    public void update() {
        System.out.println("book dao update...");
    }

}
