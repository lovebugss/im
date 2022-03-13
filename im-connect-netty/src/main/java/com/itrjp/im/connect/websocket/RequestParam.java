package com.itrjp.im.connect.websocket;

import lombok.Builder;
import lombok.Data;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 20:37
 */
@Data
@Builder
public class RequestParam {
    private String userId;
    private String roomId;
    private String token;
    private String appId;
    private String context;
}
