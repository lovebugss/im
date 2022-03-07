package com.itrjp.im.connect.listener;

import com.itrjp.im.common.protobuf.MessageProtobuf;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/6 10:37
 */
public interface MessageListener {


    /**
     * 消息
     *
     * @param messageProtobuf
     */
    void onMessage(MessageProtobuf.Message messageProtobuf);
}
