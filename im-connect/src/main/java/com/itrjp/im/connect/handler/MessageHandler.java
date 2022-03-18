package com.itrjp.im.connect.handler;

import org.springframework.stereotype.Component;

@Component
public class MessageHandler extends AbstractMessageHandler<String> {
    @Override
    public boolean checkParam() {
        return false;
    }
}
