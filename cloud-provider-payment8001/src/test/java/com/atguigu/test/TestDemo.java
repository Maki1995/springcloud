package com.atguigu.test;

import org.junit.Test;

/**
 * @author zhiqiang
 * @since 2020/5/17 17:29
 */
public class TestDemo {

    @Test
    public void testStringFormat() {
        String str = String.format("我是：%s\t%s", "张志强", "程序员");
        System.out.println(str);
        str = String.format("serviceId: %s\thost: %s\tport: %s\turi: %s\t%s", "1", "2", "3", "4","5");
        System.out.println(str);
    }
}
