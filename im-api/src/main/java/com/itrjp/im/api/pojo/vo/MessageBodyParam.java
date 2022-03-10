package com.itrjp.im.api.pojo.vo;

import com.itrjp.im.api.enums.MessageTypeEnum;
import lombok.Data;

/**
 * 消息体
 *
 * @author renjp
 * @date 2022/3/9 23:20
 */
@Data
public class MessageBodyParam {
    MessageTypeEnum messageType;
    String content;
}
