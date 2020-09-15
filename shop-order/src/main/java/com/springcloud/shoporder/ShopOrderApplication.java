package com.springcloud.shoporder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
@MapperScan(basePackages = "com.springcloud.shoporder.mapper")
@EnableDiscoveryClient
@EnableFeignClients
//@EnableFeignClients(basePackages = "com.springcloud.shoporder.feignservice")
public class ShopOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopOrderApplication.class, args);
    }

    @Bean
//    @LoadBalanced
    public RestTemplate getRestRemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
