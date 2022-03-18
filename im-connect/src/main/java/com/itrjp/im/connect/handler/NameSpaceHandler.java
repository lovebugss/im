package com.itrjp.im.connect.handler;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.itrjp.im.connect.cache.NameSpaceCache;
import com.itrjp.im.connect.enums.EventEnum;
import com.itrjp.im.connect.listener.MessageListener;
import com.itrjp.im.connect.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NameSpaceHandler {

    // 存在循环依赖, 使用ObjectProvider解决
    private final ObjectProvider<SocketIOServer> objectProvider;
    private final MessageListener messageListener;
    private final NameSpaceCache nameSpaceCache = NameSpaceCache.getInstance();

    /**
     * 创建, 如果不存在
     *
     * @param channel
     */
    public void create(String channel) {
        nameSpaceCache.putIfAbsent(channel, this::doCreate);
    }

    /**
     * 创建
     *
     * @param channel channelId
     * @return
     */
    private SocketIONamespace doCreate(String channel) {
        String namespace = "/" + channel;
        log.info("create namespace: {}", channel);
        SocketIOServer ioServer = objectProvider.getIfAvailable();
        if (ioServer == null) {
            throw new IllegalArgumentException();
        }
        SocketIONamespace socketIONamespace = ioServer.getNamespace(namespace);
        if (socketIONamespace == null) {
            socketIONamespace = ioServer.addNamespace(namespace);
            // 注册消息处理事件
            registerEvent(socketIONamespace);
        }
        return socketIONamespace;
    }

    /**
     * 注册事件
     */
    public void registerEvent(SocketIONamespace namespace) {
        log.info("register event, namespace:{}", namespace.getName());
        // 连接
        namespace.addConnectListener(messageListener::onConnect);
        // 断链
        namespace.addDisconnectListener(messageListener::onDisconnect);
        // 消息
        namespace.addEventListener(EventEnum.MESSAGE.getCode(), Message.class, messageListener::onData);
    }
}
