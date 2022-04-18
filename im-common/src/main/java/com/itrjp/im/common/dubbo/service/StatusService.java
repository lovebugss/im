package com.itrjp.im.common.dubbo.service;

import com.itrjp.im.common.dto.OnlineOfflineDTO;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 16:35
 */
public interface StatusService {

    /**
     * 获取房间UV
     *
     * @param roomId 房间ID
     * @return current uv
     */
    long getRoomUV(String roomId);

    /**
     * 获取房间PV
     *
     * @param roomId 房间ID
     * @return current pv
     */
    long getRoomPV(String roomId);



    /**
     * 上线
     *
     * @param data
     */
    boolean online(OnlineOfflineDTO data);


    CompletableFuture<Boolean> onlineAsync(OnlineOfflineDTO data);

    /**
     * 下线
     *
     * @param data
     */
    void offline(OnlineOfflineDTO data);

    CompletableFuture<Void> offlineAsync(OnlineOfflineDTO data);
}
