package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("singleton")
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("book dao save.....");
    }

    @PostConstruct
    public void init() {
        System.out.println("init.....");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy.....");
    }

}
