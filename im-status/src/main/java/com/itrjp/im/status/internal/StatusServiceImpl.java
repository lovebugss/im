package com.itrjp.im.status.internal;

import com.itrjp.im.common.dto.OnlineOfflineDTO;
import com.itrjp.im.common.dubbo.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/13 17:26
 */
@Slf4j
@DubboService
public class StatusServiceImpl implements StatusService {
    @Override
    public int getRoomUV(String roomId) {
        log.info("getRoomUV");
        return 100;
    }

    @Override
    public int getRoomPV(String roomId) {
        log.info("getRoomPV");
        return 100;
    }

    @Override
    public void online(OnlineOfflineDTO data) {
        log.info("online");
    }

    @Override
    public CompletableFuture<Void> onlineAsync(OnlineOfflineDTO data) {
        // TODO 线程池
        return CompletableFuture.supplyAsync(() -> {
            online(data);
            return null;
        });
    }


    @Override
    public void offline(OnlineOfflineDTO data) {
        log.info("offline");
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
