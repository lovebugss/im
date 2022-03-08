package com.itrjp.im.service.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * 通知
 *
 * @author renjp
 * @date 2022/3/7 23:49
 */
public interface NoticeListener {
    void onNotice(ConsumerRecord<String, byte[]> record) throws Exception;
}
