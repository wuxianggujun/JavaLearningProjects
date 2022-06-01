package com.wuxianggujun.spring;

import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //1.������·���µ������ļ�
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //�ļ�����·��Ѱ��
        //ApplicationContext ctx = new FileSystemXmlApplicationContext("C:\\Users\\MI\\IdeaProjects\\JavaLearningProjects\\Spring-10-container\\src\\main\\resources\\applicationContext.xml");
        //BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        //BookDao bookDao = ctx.getBean("bookDao",BookDao.class);
        BookDao bookDao=ctx.getBean(BookDao.class);
        bookDao.save();

    }
}
