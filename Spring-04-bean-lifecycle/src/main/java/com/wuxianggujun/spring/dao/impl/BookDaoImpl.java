package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;

public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("book dao save.....");
    }

    //��ʾbean��ʼ����Ӧ�Ĳ���
    public void init() {
        System.out.println("init ....");

    }

    //��ʾbean����ǰ��Ӧ�Ĳ���
    public void destroy() {
        System.out.println("destroy....");

    }
}
