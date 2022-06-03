package com.wuxianggujun.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({"com.wuxianggujun.spring"})
@EnableAspectJAutoProxy
public class SpringConfig {
}
