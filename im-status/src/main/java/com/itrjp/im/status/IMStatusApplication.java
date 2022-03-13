package com.itrjp.im.status;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 16:59
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo(scanBasePackages = "com.itrjp.im.status")
public class IMStatusApplication {
    public static void main(String[] args) {
        SpringApplication.run(IMStatusApplication.class, args);
    }
}

