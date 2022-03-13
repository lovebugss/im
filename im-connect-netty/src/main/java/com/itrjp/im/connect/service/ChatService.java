package com.itrjp.im.connect.service;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.connect.websocket.HandshakeData;
import com.itrjp.im.connect.websocket.WebSocketClient;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/5 23:46
 */
public interface ChatService {
    boolean isAuthorize(HandshakeData data);

    /**
     * 消息
     *
     * @param messageProtobuf
     */
    void onMessage(WebSocketClient client, MessageProtobuf.Message messageProtobuf);

    /**
     * 连接
     *
     * @param client
     */
    void onConnect(WebSocketClient client);


    void onDisconnect(WebSocketClient client);

    void onException(WebSocketClient client, Throwable exception);
}
