package com.itrjp.im.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itrjp.im.common.deserializer.LocalDateTimeDeserializer;
import com.itrjp.im.common.serializer.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author renjp
 * @since 2022-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("im_room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房间id
     */
    @TableField("room_id")
    private String roomId;

    /**
     * 是否禁言
     */
    @TableField("mute")
    private Integer mute;

    /**
     * 审计方式, 1: 人工审核, 2: 智能审核, 3: 不审核, 0: 默认, 黑词过滤
     */
    @TableField("audit_type")
    private int auditType;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 逻辑删除
     */
    @TableField("deleted")
    private Integer deleted;


}
