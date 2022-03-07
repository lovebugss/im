package com.itrjp.im.connect.service.impl;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.connect.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 聊天实现
 *
 * @author renjp
 * @date 2022/3/5 23:48
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {
    @Override
    public boolean authorize() {
        return true;
    }

    @Override
    public void onMessage(MessageProtobuf.Message message) {
        // 收到消息后， 直接通过kafka 找给业务层
    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onClose() {

    }
}
