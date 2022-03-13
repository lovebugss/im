package com.itrjp.im.service.biz.service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/8 22:42
 */
public interface NoticeService<T extends MessageOrBuilder> {
    String ONLINE = "online";
    String OFFLINE = "offline";

    boolean match(String type);

    T decode(byte[] data) throws InvalidProtocolBufferException;

    void onEvent(T data);
}
