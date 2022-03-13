package com.itrjp.im.service.listener;

import com.google.protobuf.MessageOrBuilder;
import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.service.biz.service.ChatMessageService;
import com.itrjp.im.service.biz.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class ChatMessageListener implements MessageListener, NoticeListener {

    private final List<NoticeService<MessageOrBuilder>> noticeServices;
    private final ChatMessageService chatMessageService;

    public ChatMessageListener(List<NoticeService<MessageOrBuilder>> noticeServices, ChatMessageService chatMessageService) {
        this.noticeServices = noticeServices;
        this.chatMessageService = chatMessageService;
    }

    @Override
    @KafkaListener(topics = {TOPIC_MESSAGE})
    public void onMessage(byte[] data) {
        MessageProtobuf.Message message = null;
        try {
            message = MessageProtobuf.Message.parseFrom(data);
            chatMessageService.handlerMessage(message);
        } catch (Exception e) {
            log.error("消息处理失败, message: {}", message, e);
        }
    }

    @Override
    @KafkaListener(topics = {TOPIC_NOTICE})
    public void onNotice(ConsumerRecord<String, byte[]> record) throws Exception {
        // 根据key, 去判断通知类型
        String key = record.key();
        byte[] data = record.value();

        for (NoticeService<MessageOrBuilder> noticeService : noticeServices) {
            // 匹配通知类型
            if (noticeService.match(key)) {
                MessageOrBuilder decode = noticeService.decode(data);
                if (decode != null) {
                    noticeService.onEvent(decode);
                }
                break;
            }
        }
    }
}
