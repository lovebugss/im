package com.itrjp.im.status.service.impl;

import com.itrjp.im.common.conts.RedisKeyConstant;
import com.itrjp.im.common.dto.OnlineOfflineDTO;
import com.itrjp.im.status.service.RoomService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/19 11:48
 */
@Service
public class RoomServiceImpl implements RoomService {
    private final String pvKey = RedisKeyConstant.CHANNEL_PV;
    RedisTemplate<String, String> redisTemplate;

    @Override
    public long getRoomUV(String roomId) {

        return 0;
    }

    @Override
    public long getRoomPV(String roomId) {
        String key = pvKey.replace("{roomId}", roomId);
        String pv = redisTemplate.opsForValue().get(key);
        return pv == null ? 0 : Long.parseLong(pv);
    }

    @Override
    public boolean online(OnlineOfflineDTO data) {
        String key = pvKey.replace("{roomId}", data.getRoomId());
        redisTemplate.opsForValue().increment(key);
        return true;
    }

    @Override
    public void offline(OnlineOfflineDTO data) {
        String key = pvKey.replace("{roomId}", data.getRoomId());
        redisTemplate.opsForValue().decrement(key);
    }
}
