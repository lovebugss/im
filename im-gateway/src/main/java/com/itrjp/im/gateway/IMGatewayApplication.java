package com.itrjp.im.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IMGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(IMGatewayApplication.class, args);
    }
}
