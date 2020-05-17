package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhiqiang
 * @since 2020/5/13 23:14
 */
@Mapper
public interface PaymentDao {
    int add(@Param("payment") Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
