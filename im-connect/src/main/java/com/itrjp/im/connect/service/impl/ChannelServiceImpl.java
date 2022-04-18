package com.itrjp.im.connect.service.impl;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.itrjp.im.common.dto.OnlineOfflineDTO;
import com.itrjp.im.common.dubbo.service.StatusService;
import com.itrjp.im.connect.service.ChannelService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
    @DubboReference
    private StatusService statusService;

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

    @Override
    public boolean joinChannel(UUID sessionId, SocketIONamespace namespace, HandshakeData handshakeData) {
        String name = namespace.getName();
        String userId = handshakeData.getSingleUrlParam("userId");
        OnlineOfflineDTO data = OnlineOfflineDTO.builder()
                .roomId(name.replace("/", ""))
                .userId(userId)
                // TODO 服务器地址
                .serverId("")
                .sessionId(sessionId.toString())
                .time(LocalDateTime.now())

                .build();
        CompletableFuture<Boolean> future = statusService.onlineAsync(data);
        future.whenComplete((unused, throwable) -> {
            if (throwable != null) {
                // 失败
            }
            // 成功
        });
        return true;
    }
}
