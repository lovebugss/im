package com.itrjp.im.service.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 聊天消息监听器
 *
 * @author renjp
 * @date 2022/3/7 23:33
 */
@Component
public class ChatMessageListener implements MessageListener, NoticeListener, OfflineListener, OnlineListener {


    @Override
    @KafkaListener(topics = {"im-message"})
    public void onMessage(byte[] data) {

    }

    @Override
    @KafkaListener(topics = {"im-notice"})
    public void onNotice(byte[] data) {

    }

    @Override
    public void onOffline(byte[] data) {

    }

    @Override
    public void onOnline(byte[] data) {

    }
}
