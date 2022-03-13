package com.itrjp.im.connect.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * nacos 配置
 */
@Configuration
public class NacosConfig {

    private final WebSocketProperties properties;

    public NacosConfig(WebSocketProperties properties) {
        this.properties = properties;
    }

    @Bean
    public NacosDiscoveryProperties nacosProperties() {
        NacosDiscoveryProperties nacosDiscoveryProperties = new NacosDiscoveryProperties();
        Map<String, String> metadata = nacosDiscoveryProperties.getMetadata();
        metadata.put("topic", properties.getTopic());
        metadata.put("start-time", LocalDateTime.now().toString());
        return nacosDiscoveryProperties;
    }
}
