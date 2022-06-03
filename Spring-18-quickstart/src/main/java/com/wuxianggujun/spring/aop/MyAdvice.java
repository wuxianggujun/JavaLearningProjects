package com.wuxianggujun.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(void com.wuxianggujun.spring.dao.BookDao.update())")
    private void pt() {
    }


    //定义切入点
    @Before("pt()")
    public void method() {
        System.out.println(System.currentTimeMillis());
    }
}
