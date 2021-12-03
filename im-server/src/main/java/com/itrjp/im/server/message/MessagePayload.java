package com.itrjp.im.server.message;

/**
 * 消息载体
 *
 * @author renjp
 * @date 2021/12/1 17:16
 */
public interface MessagePayload<T> {

    T getMessage();
}
