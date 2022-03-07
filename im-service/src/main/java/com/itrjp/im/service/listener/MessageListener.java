package com.itrjp.im.service.listener;

/**
 * 消息
 *
 * @author renjp
 * @date 2022/3/7 23:49
 */
public interface MessageListener {
    void onMessage(byte[] data);
}
