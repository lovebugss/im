package com.itrjp.im.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itrjp.im.service.entity.Room;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author renjp
 * @since 2022-03-12
 */
public interface IRoomService extends IService<Room> {

    /**
     * 根据房间id 获取
     *
     * @param roomId
     * @return
     */
    Room getOneByRoomId(String roomId);
}
