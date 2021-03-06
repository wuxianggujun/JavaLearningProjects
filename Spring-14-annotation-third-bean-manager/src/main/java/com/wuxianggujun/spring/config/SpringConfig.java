package com.wuxianggujun.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.wuxianggujun.spring.dao")
@Import(JdbcConfig.class)
public class SpringConfig {

}
