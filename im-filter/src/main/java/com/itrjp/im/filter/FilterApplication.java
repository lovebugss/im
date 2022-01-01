package com.itrjp.im.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 消息过滤服务
 * 程序主入口
 *
 * @author renjp
 */
@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@EnableDiscoveryClient
public class FilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class, args);
    }
}
