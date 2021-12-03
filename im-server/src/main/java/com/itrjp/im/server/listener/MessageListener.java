package com.itrjp.im.server.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.listener.DataListener;
import com.itrjp.im.server.handler.AbstractMessageHandler;
import com.itrjp.im.server.message.Message;

/**
 * 消息监听
 *
 * @author renjp
 * @date 2021/12/1 16:42
 */

public class MessageListener implements DataListener<Message> {
    private final AbstractMessageHandler<String> messageHandler;

    public MessageListener(AbstractMessageHandler<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, Message message, AckRequest ackRequest) throws Exception {
        messageHandler.handler(message);
        SocketIONamespace namespace = socketIOClient.getNamespace();
        // 消息过滤
        BroadcastOperations broadcastOperations = namespace.getBroadcastOperations();
        broadcastOperations.sendEvent("chatevent", message);
    }
}
