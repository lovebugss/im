package com.itrjp.im.connect.listener;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/6 10:37
 */
public class MessageListener {


    /**
     * 消息
     *
     * @param messageProtobuf
     */
    @KafkaListener(topics = {"${im.connect.topic}"})
    void onMessage(MessageProtobuf.Message messageProtobuf) {

    }


}
