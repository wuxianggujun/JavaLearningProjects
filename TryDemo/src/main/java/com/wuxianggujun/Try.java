package com.wuxianggujun;

import java.util.function.Supplier;

/**
 * @author WuXiangGuJun
 * @create 2023-03-11 11:49
 **/
public interface Try<T> {
    //静态方法通常用于在接口中提供一些公共的工具方法
    static <T> Result<T> run(Supplier<T> supplier) {
        try {
            T value = supplier.get();
            return Result.ok(value);
        } catch (Exception e) {
            return Result.error(e);
        }
    }
}
