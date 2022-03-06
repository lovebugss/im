package com.itrjp.im.connect.config;

import com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * nacos 配置
 */
@Configuration
public class NacosConfig {

    private final NacosAutoServiceRegistration nacosAutoServiceRegistration;
    private final WebSocketProperties webSocketProperties;

    public NacosConfig(NacosAutoServiceRegistration nacosAutoServiceRegistration, WebSocketProperties webSocketProperties) {
        this.nacosAutoServiceRegistration = nacosAutoServiceRegistration;
        this.webSocketProperties = webSocketProperties;
    }

    @PostConstruct
    public void init() {
        // 手动注册到 nacos
        nacosAutoServiceRegistration.setPort(webSocketProperties.getPort());
        nacosAutoServiceRegistration.start();
    }

}
