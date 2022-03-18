package com.itrjp.im.connect.config;

import com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class NacosConfig {
    @Autowired
    private IMServerProperties imServerProperties;
    @Autowired
    private NacosAutoServiceRegistration nacosAutoServiceRegistration;

    @PostConstruct
    public void init() {
        nacosAutoServiceRegistration.setPort(imServerProperties.getPort());
        nacosAutoServiceRegistration.start();
    }

}
