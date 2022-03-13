package com.itrjp.im.connect.websocket;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * HandshakeData
 *
 * @author renjp
 * @date 2022/3/10 23:01
 */
@Getter
@ToString
public class HandshakeData implements Serializable {
    private final LocalDateTime time = LocalDateTime.now();
    private RequestParam param;
    private String url;

    public HandshakeData() {
    }

    public HandshakeData(RequestParam urlParams, String url) {
        this.url = url;
        this.param = urlParams;
    }
}
