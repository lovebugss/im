package com.itrjp.im.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 程序主入口
 *
 * @author renjp
 * @date 2022/2/22 11:49
 */
@SpringBootApplication
@EnableDiscoveryClient
public class IMServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IMServiceApplication.class, args);
    }
}
