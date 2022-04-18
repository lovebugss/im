package com.itrjp.im.connect.listener;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.corundumstudio.socketio.listener.PingListener;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.itrjp.im.connect.enums.EventEnum;
import com.itrjp.im.connect.service.ChannelService;
import com.itrjp.im.connect.service.MessageService;
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
public class IMMessageListener implements DataListener<byte[]>, ConnectListener, DisconnectListener, PingListener {
    private final ChannelService channelService;
    private final MessageService messageService;

    public IMMessageListener(ChannelService channelService, MessageService messageService) {
        this.channelService = channelService;
        this.messageService = messageService;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, byte[] message, AckRequest ackRequest) throws Exception {
        log.info("onData...");


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

    /**
     * 下线
     *
     * @param socketIOClient
     */
    @Override
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
    }

    /**
     * 上线
     *
     * @param socketIOClient
     */
    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        UUID sessionId = socketIOClient.getSessionId();
        // 检查命名空间/房间是否存在
        socketIOClient.joinRoom("");
        SocketIONamespace namespace = socketIOClient.getNamespace();
        HandshakeData handshakeData = socketIOClient.getHandshakeData();
        boolean joined = channelService.joinChannel(sessionId, namespace, handshakeData);
        if(joined){
            socketIOClient.getNamespace()
                    .getBroadcastOperations()
                    .sendEvent(EventEnum.NOTICE.toString(), (Object) new  byte[0]);
        }

    }

    @Override
    public void onPing(SocketIOClient client) {

    }
}
