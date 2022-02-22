package com.itrjp.im.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 加入房间
 *
 * @author renjp
 * @date 2022/2/22 17:29
 */
public class JoinRoomDTO {

    @JsonProperty("rid")
    private String roomId;
    @JsonProperty("uid")
    private String userId;
}
