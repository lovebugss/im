package com.itrjp.im.status.listener;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * nacos 监听器
 *
 * @author renjp
 * @date 2022/3/19 19:36
 */
//@Component
@Order(Integer.MIN_VALUE)
public class NacosListener {
    @Autowired
    NacosServiceManager nacosServiceManager;
    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties())
                .subscribe("im-connect", new EventListener() {
                    @Override
                    public void onEvent(Event event) {
                        System.out.println("event");
                    }
                });
    }
}
