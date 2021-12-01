package com.itrjp.im.server.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.itrjp.im.server.message.Message;

/**
 * 消息监听
 *
 * @author renjp
 * @date 2021/12/1 16:42
 */

public class MessageListener implements DataListener<Message> {


    @Override
    public void onData(SocketIOClient socketIOClient, Message message, AckRequest ackRequest) throws Exception {

        // 消息过滤
    }
}
