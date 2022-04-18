package com.itrjp.im.connect.service;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;

import java.util.UUID;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/2/22 11:52
 */
public interface ChannelService {
    /**
     * 加入房间
     */
    void joinRoom(SocketIONamespace room, String userId);

    boolean joinChannel(UUID sessionId, SocketIONamespace namespace, HandshakeData handshakeData);
}
