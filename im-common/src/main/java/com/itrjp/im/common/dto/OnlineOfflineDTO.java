package com.itrjp.im.common.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 19:03
 */
@Data
@Builder
public class OnlineOfflineDTO {
    private String roomId;
    private String userId;
    private String sessionId;
    private LocalDateTime time;
    private String serverId;
}
