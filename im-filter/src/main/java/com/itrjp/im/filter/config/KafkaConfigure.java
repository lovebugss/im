package com.itrjp.im.filter.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * kafka 配置类
 *
 * @author renjp
 * @date 2022/1/16 18:00
 */
@Configuration
public class KafkaConfigure {
    @Value("${im.filter.topic:filter-topic}")
    public String filterTopic;

    @Bean
    NewTopic filterTopic() {
        return TopicBuilder
                .name(filterTopic)
                .build();
    }
}
