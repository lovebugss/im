package com.itrjp.im.connect.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import static com.itrjp.im.common.conts.KafkaConstant.TOPIC_CONNECT;

/**
 * @author renjp
 */
//@Configuration
//@EnableKafka
public class KafkaConfigure {


    @Bean
    NewTopic connectTopic() {
        return TopicBuilder
                .name(TOPIC_CONNECT)
                .build();
    }
}
