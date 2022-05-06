package com.itrjp.im.service.biz.service.impl;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.service.biz.service.ChatMessageService;
import com.itrjp.im.service.biz.service.audit.AuditResult;
import com.itrjp.im.service.biz.service.audit.AuditStatus;
import com.itrjp.im.service.biz.service.audit.MessageAuditHandler;
import com.itrjp.im.service.entity.Room;
import com.itrjp.im.service.service.IRoomService;
import com.itrjp.im.service.service.MQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MessageHandler
 *
 * @author renjp
 * @date 2022/3/8 22:41
 */

@Slf4j
@Service
public class ChatMessageServiceImpl implements ChatMessageService {
    private final List<MessageAuditHandler> messageAuditHandler;
    private final IRoomService roomService;
    private final MQService mqService;


    public ChatMessageServiceImpl(List<MessageAuditHandler> messageAuditHandler, IRoomService roomService, MQService mqService) {
        this.messageAuditHandler = messageAuditHandler;
        this.roomService = roomService;
        this.mqService = mqService;
    }

    @Override
    public void handlerMessage(MessageProtobuf.Message data) {
        log.info("handler message: {}", data.toString());

        String roomId = data.getRoomId();
        if (StringUtils.isBlank(roomId)) {
            log.warn("miss roomId");
            return;
        }
        Room roomConfig = getRoomConfig(roomId);
        // 判断用户是否禁言
        String sender = data.getSender();

        // 消息内容审核
        AuditResult auditResult = messageAudit(data, roomConfig);
        AuditStatus status = auditResult.getStatus();
        switch (status) {
            case SUCCEEDED:
                // 消息落盘
                // 消息广播
                sendMessage(data);
                break;
            case FAILED:
                // 消息落盘
                break;
            case UNDER_REVIEW:
                //
                break;
        }
    }

    /**
     * 发送消息
     *
     * @param data
     */
    private void sendMessage(MessageProtobuf.Message data) {

        // 群聊
        // 私聊
    }

    private void send() {

    }

    private Room getRoomConfig(String roomId) {
        return roomService.getOneByRoomId(roomId);
    }

    /**
     * 消息审核
     * 1. 文本消息
     *
     * @param data
     * @param room
     */
    private AuditResult messageAudit(MessageProtobuf.Message data, Room room) {
        // 消息类型
        for (MessageAuditHandler auditService : messageAuditHandler) {
            // 根据消息类型, 触发审核逻辑
            if (auditService.match(room.getAuditType())) {
                return auditService.audit(data, room);
            }
        }
        log.info("未找到相应审核方式, message: {}", data);
        throw new IllegalArgumentException("无效的审核方式");
    }
}
