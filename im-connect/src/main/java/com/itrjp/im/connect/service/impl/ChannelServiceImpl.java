package com.itrjp.im.connect.service.impl;

import com.corundumstudio.socketio.SocketIONamespace;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.itrjp.im.connect.service.ChannelService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/2/22 11:53
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    private final Cache<String, SocketIONamespace> namespaceCache = CacheBuilder.newBuilder().build();

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    public ChannelServiceImpl(KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void joinRoom(SocketIONamespace namespace, String userId) {
        String name = namespace.getName();
        namespaceCache.put(name, namespace);

        // 发送上线消息到kafka, 进行数据统计
        kafkaTemplate.send("", "");
    }
}
