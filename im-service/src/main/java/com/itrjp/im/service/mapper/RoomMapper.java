package com.itrjp.im.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itrjp.im.service.entity.Room;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author renjp
 * @since 2022-03-12
 */
public interface RoomMapper extends BaseMapper<Room> {

    @Select("select * from im_room where room_id = '${roomId}'")
    Room selectByRoomId(@Param("roomId") String roomId);
}
