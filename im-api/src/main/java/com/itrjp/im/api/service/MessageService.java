package com.itrjp.im.api.service;

import com.itrjp.im.api.pojo.MessageInfo;
import com.itrjp.im.api.pojo.vo.MessageParam;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/2/22 18:06
 */
public interface MessageService {
    MessageInfo queryMessageByMsgId(String msgId);

    String sendMessage(MessageParam messageParam);
}
