package com.itrjp.im.server.listener;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.itrjp.im.server.enums.EventEnum;
import com.itrjp.im.server.handler.AbstractMessageHandler;
import com.itrjp.im.server.message.Message;
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
    private final Cache<String, SocketIOClient> cache = CacheBuilder.newBuilder().build();
    private final SocketIOServer socketIOServer;

    public MessageListener(AbstractMessageHandler<String> messageHandler, SocketIOServer socketIOServer) {
        this.messageHandler = messageHandler;
        this.socketIOServer = socketIOServer;
    }

    @OnEvent("message")
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
//        socketIOServer.getBroadcastOperations().sendEvent(EventEnum.MESSAGE.getCode(), message);
//
        broadcastOperations.sendEvent(EventEnum.MESSAGE.getCode(), message);
//        cache.asMap().forEach((key, val) -> {
//            if (val.isChannelOpen()) {
//                val.sendEvent(EventEnum.MESSAGE.getCode(), message);
//            }
//        });

    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient socketIOClient) {
        log.info("onDisconnect...");
        String sessionId = socketIOClient.getSessionId().toString();
        log.info("onDisconnect, client: {}", sessionId);
        // 检查命名空间/房间是否存在
        SocketIONamespace namespace = socketIOClient.getNamespace();
        HandshakeData handshakeData = socketIOClient.getHandshakeData();
        String room = handshakeData.getSingleUrlParam("room");
        log.info("current namespace:{}, room: {}", namespace, room);
        socketIOClient.disconnect();
        cache.invalidate(sessionId);
    }

    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {
        log.info("onConnect...");
        UUID sessionId = socketIOClient.getSessionId();
        log.info("onConnect, client: {}", sessionId);
        // 检查命名空间/房间是否存在

        cache.put(sessionId.toString(), socketIOClient);
        SocketIONamespace namespace = socketIOClient.getNamespace();
        HandshakeData handshakeData = socketIOClient.getHandshakeData();
        String room = handshakeData.getSingleUrlParam("room");
        log.info("current namespace:{}, room: {}", namespace, room);
        socketIOClient.joinRoom(room);
    }
}
