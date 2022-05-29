package com.wuxianggujun.spring.factory;

import com.wuxianggujun.spring.dao.OrderDao;
import com.wuxianggujun.spring.dao.impl.OrderDaoImpl;

public class OrderDaoFactory {

    public static OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }
}
