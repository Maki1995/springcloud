package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhiqiang
 * @since 2020/5/13 23:23
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    String serverPort;

    @PostMapping(value = "/payment/add")
    public CommonResult add(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);
        int i = 10 / 2;
        System.out.println(i);
        if (result > 0) {  //成功
            return new CommonResult(200, "插入数据库成功，端口号为：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果：" + payment);
        if (payment != null) {  //说明有数据，能查询成功
            return new CommonResult(200, "查询成功，端口号为：" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询ID：" + id, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("-------------- service: " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(String.format("serviceId: %s\thost: %s\tport: %s\turi: %s", instance.getInstanceId(), instance.getHost(), instance.getPort(), instance.getUri()));
//            String str = String.format("serviceId: %s\thost: %s\tport: %s\turi: %s", "1", "2", "3", "4");
//            log.info(str);
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
