package com.woasis.consulconsumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@MapperScan("com.woasis.consulconsumer.mapper")
public class ConsulConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ConsulConsumerApplication.class, args);
        Object bean = run.getBean("ConsumerService");
    }
}
