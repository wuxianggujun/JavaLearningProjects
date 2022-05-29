package com.wuxianggujun.spring_ioc_simple.service.impl;

import com.wuxianggujun.spring_ioc_simple.dao.BookDao;
import com.wuxianggujun.spring_ioc_simple.service.BookService;

public class BookServiceImpl implements BookService {

    //ɾ��ҵ�����ʹ��new�ķ�ʽ������dao����
    private BookDao bookDao;

    @Override
    public void save() {
        System.out.println("Book Service save...");
        bookDao.save();
    }

    //�ṩ��Ӧ��set����
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

}
