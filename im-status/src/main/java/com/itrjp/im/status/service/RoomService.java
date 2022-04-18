package com.itrjp.im.status.service;

import com.itrjp.im.common.dto.OnlineOfflineDTO;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 18:17
 */
public interface RoomService {
    long getRoomUV(String roomId);

    long getRoomPV(String roomId);

    boolean online(OnlineOfflineDTO data);

    void offline(OnlineOfflineDTO data);
}
