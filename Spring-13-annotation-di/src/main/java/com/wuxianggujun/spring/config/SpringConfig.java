package com.wuxianggujun.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.wuxianggujun.spring"})
@PropertySource("jdbc.properties")
public class SpringConfig {


}
