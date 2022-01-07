package com.itrjp.im.connect.listener;

import com.corundumstudio.socketio.*;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.itrjp.im.connect.enums.EventEnum;
import com.itrjp.im.connect.handler.AbstractMessageHandler;
import com.itrjp.im.connect.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;


/**
 * 消息监听
 *
 * @author renjp
 * @date 2021/12/1 16:42
 */
@Slf4j
@Component
public class MessageListener {
    private final AbstractMessageHandler<String> messageHandler;
    private final Cache<UUID, SocketIOClient> cache = CacheBuilder.newBuilder().build();

    public MessageListener(AbstractMessageHandler<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void onData(SocketIOClient socketIOClient, Message message, AckRequest ackRequest) throws Exception {
        log.info("onData...");
        messageHandler.handler(message);
        UUID sessionId = socketIOClient.getSessionId();
        log.info("current client: [{}]", sessionId);
        SocketIONamespace namespace = socketIOClient.getNamespace();
        HandshakeData handshakeData = socketIOClient.getHandshakeData();
        String room = handshakeData.getSingleUrlParam("room");
        log.info("current namespace: [{}], room: {}", namespace.getName(), room);
        Collection<SocketIOClient> allClients = namespace.getAllClients();
        allClients.forEach((socketIOClient1 -> log.info("foreach: {}", socketIOClient1.getSessionId())));
//        // 消息过滤
        BroadcastOperations broadcastOperations = namespace.getBroadcastOperations();
        broadcastOperations.sendEvent(EventEnum.MESSAGE.toString(), message);

    }

    public void onDisconnect(SocketIOClient socketIOClient) {
        log.info("onDisconnect...");
        UUID sessionId = socketIOClient.getSessionId();
        log.info("onDisconnect, client: {}", sessionId);
        // 检查命名空间/房间是否存在
        SocketIONamespace namespace = socketIOClient.getNamespace();
        HandshakeData handshakeData = socketIOClient.getHandshakeData();
        String room = handshakeData.getSingleUrlParam("room");
        log.info("current namespace:{}, room: {}", namespace.getName(), room);
        socketIOClient.disconnect();
        cache.invalidate(sessionId);
    }

    public void onConnect(SocketIOClient socketIOClient) {
        log.info("onConnect...");
        UUID sessionId = socketIOClient.getSessionId();
        log.info("onConnect, client: {}", sessionId);
        // 检查命名空间/房间是否存在

        cache.put(sessionId, socketIOClient);
        SocketIONamespace namespace = socketIOClient.getNamespace();
        HandshakeData handshakeData = socketIOClient.getHandshakeData();
        String room = handshakeData.getSingleUrlParam("room");
        String userId = handshakeData.getSingleUrlParam("userId");
        log.info("current namespace:{}, room: {}", namespace.getName(), room);
        namespace.getBroadcastOperations().sendEvent(EventEnum.NOTICE.getCode(), userId);
    }
}
