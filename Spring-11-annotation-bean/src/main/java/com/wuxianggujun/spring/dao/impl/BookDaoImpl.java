package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.stereotype.Component;

@Component("bookDao")
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("book dao save.....");
    }

}
