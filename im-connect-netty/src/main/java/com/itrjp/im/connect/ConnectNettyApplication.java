package com.itrjp.im.connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 程序启动类
 *
 * @author renjp
 * @date 2022/3/5 15:30
 */
@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@EnableDiscoveryClient
public class ConnectNettyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConnectNettyApplication.class, args);
    }
}
