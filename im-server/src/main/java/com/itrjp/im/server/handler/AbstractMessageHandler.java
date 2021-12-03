package com.itrjp.im.server.handler;

import com.itrjp.im.server.message.MessagePayload;

/**
 * 消息处理逻辑
 *
 * @author renjp
 */
public abstract class AbstractMessageHandler<T> implements ParamChecked {
    public void handler(MessagePayload<T> messagePayload) {
        // 参数校验
        boolean isCheck = checkParam();
        if (isCheck) {

        }
    }

}
