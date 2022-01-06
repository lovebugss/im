package com.itrjp.im.connect.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author renjp
 */
@Configuration
@EnableKafka
public class KafkaConfigure {

    @Value("${im.kafka.topic:connectTopic}")
    private String connectTopic;

    @Bean
    NewTopic connectTopic() {
        return TopicBuilder.name(connectTopic).build();
    }
}
