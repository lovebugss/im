package com.itrjp.im.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itrjp.im.service.entity.Room;
import com.itrjp.im.service.mapper.RoomMapper;
import com.itrjp.im.service.service.IRoomService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author renjp
 * @since 2022-03-12
 */
@Service
@CacheConfig(cacheNames = {"im:room"})
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    @Override
    @Cacheable(unless = "#result == null")
    public Room getOneByRoomId(String roomId) {
        return this.baseMapper.selectByRoomId(roomId);
    }
}
