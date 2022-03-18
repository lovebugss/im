package com.itrjp.im.connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 程序主入口
 * 1. 初始化配置
 * 2. 启动server
 *
 * @author renjp
 */
@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@EnableDiscoveryClient
public class ConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConnectApplication.class, args);
    }
}
