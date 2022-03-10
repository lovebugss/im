package com.itrjp.im.api.controller;

import com.itrjp.im.api.pojo.MessageInfo;
import com.itrjp.im.api.pojo.vo.MessageParam;
import com.itrjp.im.api.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("history/{channelId}")
    public ResponseEntity<?> queryHistoryMessage(@PathVariable("channelId") String channelId) {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }

    /**
     * 发送消息
     *
     * @return
     */
    @PostMapping("send")
    public ResponseEntity<?> sendMessage(MessageParam messageParam) {
        String msgId = messageService.sendMessage(messageParam);
        return ResponseEntity.ok(msgId);
    }

    /**
     * 查询单个消息
     *
     * @param msgId
     * @return
     */
    @GetMapping("{msgId}")
    public ResponseEntity<MessageInfo> queryMessage(@PathVariable("msgId") String msgId) {
        MessageInfo messageInfo = messageService.queryMessageByMsgId(msgId);
        return ResponseEntity.ok(messageInfo);
    }
}
