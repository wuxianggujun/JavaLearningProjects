package com.wuxianggujun.spring;

import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class AppForBeanFactory {
    public static void main(String[] args) {
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory bf = new XmlBeanFactory(resource);
        //beanFactory����������е�bean��Ϊ�ӳټ���
        BookDao bookDao = bf.getBean(BookDao.class);
        bookDao.save();
    }
}
