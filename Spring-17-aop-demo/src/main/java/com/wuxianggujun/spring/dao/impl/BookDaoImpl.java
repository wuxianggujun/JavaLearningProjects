package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        //��¼����ǰִ��ʱ�䣨��ʼʱ�䣩
        Long startTime = System.currentTimeMillis();
        //ҵ��ִ�����
        for (int i = 0; i < 10000; i++) {
            System.out.println("book dao save...");
        }
        //��¼����ǰִ��ʱ�䣨����ʱ�䣩
        Long endTime = System.currentTimeMillis();
        //����ʱ���
        Long totalTime = endTime - startTime;
        //�����Ϣ
        System.out.println("ִ���������ʱ��" + totalTime + "ms");
    }

    public void update() {
        System.out.println("book dao update...");
    }

    public void delete() {
        System.out.println("book dao delete...");
    }

    public void select() {
        System.out.println("book dao select...");
    }
}
