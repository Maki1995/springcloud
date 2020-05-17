package com.atguigu.springcloud.service;

/**
 * @author zhiqiang
 * @since 2020/5/17 21:34
 */
public interface PaymentService {
    String paymentInfo_ok(Integer id);

    String paymentInfo_timeOut(Integer id);
}
