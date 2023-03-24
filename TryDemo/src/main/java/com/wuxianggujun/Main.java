package com.wuxianggujun;

import java.util.function.Supplier;

/**
 * @author WuXiangGuJun
 * @create ${YEAR}-${MONTH}-${DAY} ${TIME}
 **/
public class Main {
    public static void main(String[] args) {
        int id=Try.run(()-> {
            return 1 / 0;
        }).unwrap();
    }
    
}