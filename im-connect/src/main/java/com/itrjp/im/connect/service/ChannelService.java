package com.itrjp.im.connect.service;

import com.corundumstudio.socketio.SocketIONamespace;

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
}
