package com.itrjp.im.server.message;

import lombok.Data;

@Data
public class Message implements MessagePayload<String> {
    String form;
    String to;
    MessageType type;
    String body;
    private String userName;
    private String message;
}
