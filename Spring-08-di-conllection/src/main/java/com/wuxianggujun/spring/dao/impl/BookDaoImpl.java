package com.wuxianggujun.spring.dao.impl;

import com.wuxianggujun.spring.dao.BookDao;

import java.util.*;

public class BookDaoImpl implements BookDao {

    private int[] array;

    private List<String> list;

    private Set<String> set;

    private Map<String, String> map;

    private Properties properties;

    @Override
    public void save() {
        System.out.println("Book dao save....");

        System.out.println("�������飺" + Arrays.toString(array));

        System.out.println("����List��" + list);

        System.out.println("����Set��" + set);

        System.out.println("����Map��" + map);

        System.out.println("����Properties:" + properties);
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
