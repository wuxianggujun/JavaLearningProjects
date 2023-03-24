package com.wuxianggujun.miniioc.annotation;

import com.wuxianggujun.miniioc.constant.enums.ScopeConst;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Autowired {
    /**
     * 指定依赖名称
     *
     * @return
     */
    String value() default "";

    /**
     * 指定创建类的模式 单利还是多例
     *
     * @return
     */
    ScopeConst scope() default ScopeConst.SINGLETON;
}
