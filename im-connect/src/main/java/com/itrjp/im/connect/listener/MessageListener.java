package com.itrjp.im.connect.listener;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ClientListeners;
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
    private final Cache<String, SocketIOClient> cache = CacheBuilder.newBuilder().build();
//    private final SocketIOServer socketIOServer;

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
        broadcastOperations.sendEvent(EventEnum.MESSAGE.getCode(), message);

    }

    public void onDisconnect(SocketIOClient socketIOClient) {
        log.info("onDisconnect...");
        String sessionId = socketIOClient.getSessionId().toString();
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

        cache.put(sessionId.toString(), socketIOClient);
        SocketIONamespace namespace = socketIOClient.getNamespace();
        HandshakeData handshakeData = socketIOClient.getHandshakeData();
        String room = handshakeData.getSingleUrlParam("room");
        log.info("current namespace:{}, room: {}", namespace.getName(), room);
        socketIOClient.joinRoom(room);
        socketIOClient.sendEvent("list", new String[]{"zhangsan", "lisi"});
    }

    public void registerEvent(ClientListeners namespace) {
        // 连接
        namespace.addConnectListener(this::onConnect);
        // 断链
        namespace.addDisconnectListener(this::onDisconnect);
        // 消息
        namespace.addEventListener(EventEnum.MESSAGE.getCode(), Message.class, this::onData);
    }
}
