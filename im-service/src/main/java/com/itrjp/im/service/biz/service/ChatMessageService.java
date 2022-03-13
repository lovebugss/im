package com.itrjp.im.service.biz.service;

import com.itrjp.im.common.protobuf.MessageProtobuf;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/12 19:43
 */
public interface ChatMessageService {
    void handlerMessage(MessageProtobuf.Message data);
}
