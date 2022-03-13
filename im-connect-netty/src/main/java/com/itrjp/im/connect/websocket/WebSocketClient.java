package com.itrjp.im.connect.websocket;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 19:26
 */
public interface WebSocketClient {

    String getUserId();

    String getRoomId();

    String getSessionId();

    HandshakeData getHandshakeData();


}
