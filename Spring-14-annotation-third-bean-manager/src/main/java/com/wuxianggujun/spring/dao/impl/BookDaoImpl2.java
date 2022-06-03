package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository("bookDao2")
public class BookDaoImpl2 implements BookDao {

    @Override
    public void save() {
        System.out.println("book dao2 save.....");
    }

}
