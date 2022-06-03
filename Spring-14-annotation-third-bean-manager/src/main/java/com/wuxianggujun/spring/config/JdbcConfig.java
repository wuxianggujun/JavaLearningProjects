package com.wuxianggujun.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.wuxianggujun.spring.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

//@Configuration
public class JdbcConfig {
    //定义一个方法获得要管理的对象
    //添加@Bean表示当前方法的返回值是一个bean
    @Value("com.mysql.jdbc.Driver")
    private String driver;
    @Value("jdbc:mysql://localhost:3306/spring_db")
    private String url;
    @Value("root")
    private String username;
    @Value("root")
    private String password;

    @Bean("dataSource")
    public DataSource dataSource(BookDao bookDao) {
        System.out.println(bookDao);
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
