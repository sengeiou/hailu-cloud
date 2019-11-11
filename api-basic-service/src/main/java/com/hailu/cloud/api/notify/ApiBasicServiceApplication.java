package com.hailu.cloud.api.notify;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhijie
 */
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.hailu.cloud.**.dao"})
@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class ApiBasicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiBasicServiceApplication.class, args);
    }

}
