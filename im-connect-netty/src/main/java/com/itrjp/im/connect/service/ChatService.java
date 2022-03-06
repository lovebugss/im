package com.itrjp.im.connect.service;

import com.itrjp.im.connect.listener.MessageListener;
import com.itrjp.im.connect.protobuf.MessageProtobuf;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/5 23:46
 */
public interface ChatService extends MessageListener {

    boolean authorize();


    /**
     * 连接
     */
    void onConnect();

    /**
     * 关闭
     */
    void onClose();
}
