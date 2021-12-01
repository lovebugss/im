package com.itrjp.im.server.converter;

import com.itrjp.im.server.message.MessagePayload;

/**
 * 消息转换器
 *
 * @author renjp
 * @date 2021/12/1 17:13
 */
public interface MessageConverter<T> {

    /**
     * 消息转换
     *
     * @param data 消息
     * @return MessagePayload
     */
    MessagePayload convert(T data);
}
