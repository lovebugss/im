package com.itrjp.im.server.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * 消息监听
 *
 * @author renjp
 * @date 2021/12/1 16:42
 */
public class MessageListener implements DataListener {
    @Override
    public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {

    }
}
