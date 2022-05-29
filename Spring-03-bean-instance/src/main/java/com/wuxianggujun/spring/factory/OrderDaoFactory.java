package com.wuxianggujun.spring.factory;

import com.wuxianggujun.spring.dao.OrderDao;
import com.wuxianggujun.spring.dao.impl.OrderDaoImpl;

public class OrderDaoFactory {

    //һ����̬�Ĺ���
    public static OrderDao getOrderDao() {
        System.out.println("factory setup....");
        return new OrderDaoImpl();
    }
}
