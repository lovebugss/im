package com.itrjp.im.api.pojo.vo;

import lombok.Data;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/9 23:11
 */
@Data
public class MessageParam {
    private String form;
    private String to;
    private long timestamp;
    private MessageBodyParam messageBody;
}
