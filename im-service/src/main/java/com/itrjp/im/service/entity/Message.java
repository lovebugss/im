package com.itrjp.im.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("im_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 消息id
     */
    @TableField("message_id")
    private String messageId;

    /**
     * 消息体
     */
    @TableField("content")
    private String content;

    /**
     * 房间id
     */
    @TableField("room_id")
    private String roomId;

    /**
     * 消息类型
     */
    @TableField("type")
    private String type;

    /**
     * 审核类型
     */
    @TableField("audit_type")
    private String auditType;

    /**
     * 发送者
     */
    @TableField("sender")
    private String sender;

    /**
     * 接受者
     */
    @TableField("receiver")
    private String receiver;

    /**
     * 时间
     */
    @TableField("time")
    private LocalDateTime time;


}
