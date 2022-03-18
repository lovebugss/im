package com.itrjp.im.connect.config;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

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

    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // See https://kafka.apache.org/documentation/#producerconfigs for more properties
        return props;
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate(ProducerFactory<Integer, String> producerFactory) {
        // KafkaTemplate构造函数中输入生产者工厂配置
        return new KafkaTemplate<Integer, String>(producerFactory);
    }
}
