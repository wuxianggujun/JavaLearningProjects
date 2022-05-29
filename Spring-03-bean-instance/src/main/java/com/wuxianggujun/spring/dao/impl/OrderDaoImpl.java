package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.OrderDao;

public class OrderDaoImpl implements OrderDao{
    @Override
    public void save() {
        System.out.println("order dao save....");
    }
}
