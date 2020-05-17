package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * @author zhiqiang
 * @since 2020/5/13 23:19
 */
public interface PaymentService {

    int create(Payment payment); //写

    Payment getPaymentById(Long id);  //读取
}