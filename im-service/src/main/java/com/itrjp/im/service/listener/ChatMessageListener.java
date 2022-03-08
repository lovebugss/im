package com.itrjp.im.service.listener;

import com.itrjp.im.service.service.MessageService;
import com.itrjp.im.service.service.NoticeService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.itrjp.im.common.conts.KafkaConstant.TOPIC_MESSAGE;
import static com.itrjp.im.common.conts.KafkaConstant.TOPIC_NOTICE;

/**
 * 聊天消息监听器
 *
 * @author renjp
 * @date 2022/3/7 23:33
 */
@Component
public class ChatMessageListener implements MessageListener, NoticeListener {

    private final List<NoticeService> noticeServiceList;
    private final MessageService messageService;

    public ChatMessageListener(List<NoticeService> noticeServiceList, MessageService messageService) {
        this.noticeServiceList = noticeServiceList;
        this.messageService = messageService;
    }

    @Override
    @KafkaListener(topics = {TOPIC_MESSAGE})
    public void onMessage(byte[] data) {
        messageService.handlerMessage(data);
    }

    @Override
    @KafkaListener(topics = {TOPIC_NOTICE})
    public void onNotice(ConsumerRecord<String, byte[]> record) throws Exception {
        // 根据key, 去判断通知类型
        String key = record.key();
        byte[] data = record.value();

        for (NoticeService noticeService : noticeServiceList) {
            // 匹配响应通知类型
            if (noticeService.match(key)) {
                noticeService.onEvent(data);
                break;
            }
        }
    }
}
