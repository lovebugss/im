package com.itrjp.im.connect.handler;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.itrjp.im.connect.listener.MessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NameSpaceHandler {

    private final ObjectProvider<SocketIOServer> objectProvider;
    private final MessageListener messageListener;


    public Optional<SocketIONamespace> create(String channel) {
        String namespace = "/" + channel;
        log.info("create namespace: {}", channel);
        SocketIOServer ioServer = objectProvider.getIfAvailable();
        if (ioServer == null) {
            return Optional.empty();
        }
        SocketIONamespace socketIONamespace = ioServer.getNamespace(namespace);
        // TODO 多线程问题,
        if (socketIONamespace == null) {
            socketIONamespace = ioServer.addNamespace(namespace);
            // 注册消息处理事件
            messageListener.registerEvent(socketIONamespace);
        }
        return Optional.of(socketIONamespace);
    }
}
