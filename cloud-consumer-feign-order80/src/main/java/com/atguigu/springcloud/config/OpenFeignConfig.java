package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhiqiang
 * @since 2020/5/17 21:17
 */
@Configuration
public class OpenFeignConfig {
    @Bean
    Logger.Level feignLoggerLever() {
        return Logger.Level.FULL;
    }
}
