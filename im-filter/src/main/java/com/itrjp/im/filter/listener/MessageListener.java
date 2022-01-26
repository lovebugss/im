package com.itrjp.im.filter.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import static com.itrjp.im.common.conts.KafkaConstant.TOPIC_FILTER;

/**
 * 消息监听器
 *
 * @author renjp
 * @date 2022/1/16 18:09
 */
@Configuration
public class MessageListener {

    @KafkaListener(topics = {TOPIC_FILTER}, groupId = "filter-group")
    public void processMessage(String content) {

    }
}
