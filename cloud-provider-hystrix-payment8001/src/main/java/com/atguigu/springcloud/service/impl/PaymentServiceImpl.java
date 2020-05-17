package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zhiqiang
 * @since 2020/5/17 21:35
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return String.format("线程池：%s\tpaymentInfo_ok,id：%d\t哈哈哈",
                Thread.currentThread().getName(), id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "payment_timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_timeOut(Integer id) {
        int timeNumber = 5000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("线程池：%s\tpaymentInfo_timeout,id：%d\t呜呜呜\t%d",
                Thread.currentThread().getName(), id, timeNumber);
    }

    /**
     * 兜底方法
     * @param id
     * @return
     */
    public String payment_timeoutHandler(Integer id) {
        return String.format("线程池：%s\t系统繁忙，请稍后再试，id：%d\t哭了呜哇", Thread.currentThread().getName(), id);
    }
}
