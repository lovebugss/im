package com.itrjp.im.status.internal;

import com.itrjp.im.common.dto.OnlineOfflineDTO;
import com.itrjp.im.common.dubbo.service.StatusService;
import com.itrjp.im.status.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 17:26
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final RoomService roomService;


    @Override
    public long getRoomUV(String roomId) {
        log.info("getRoomUV");

        return roomService.getRoomUV(roomId);
    }

    @Override
    public long getRoomPV(String roomId) {
        log.info("getRoomPV");
        return roomService.getRoomPV(roomId);
    }

    @Override
    public boolean online(OnlineOfflineDTO data) {
        log.info("online");

        return roomService.online(data);
    }

    @Override
    public CompletableFuture<Boolean> onlineAsync(OnlineOfflineDTO data) {
        // TODO 线程池
        return CompletableFuture.supplyAsync(() -> {

            return online(data);
        });
    }


    @Override
    public void offline(OnlineOfflineDTO data) {
        log.info("offline");
        roomService.offline(data);
    }

    @Override
    public CompletableFuture<Void> offlineAsync(OnlineOfflineDTO data) {
        // TODO 线程池
        return CompletableFuture.supplyAsync(() -> {
            offline(data);
            return null;
        });
    }
}
