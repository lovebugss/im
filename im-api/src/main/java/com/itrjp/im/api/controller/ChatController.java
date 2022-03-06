package com.itrjp.im.api.controller;

import com.itrjp.im.api.pojo.vo.ChatConfigVo;
import com.itrjp.im.api.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/5 11:18
 */
@RestController
@RequestMapping("chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("config")
    public ResponseEntity<ChatConfigVo> getChatConfig(String roomId) {

        ChatConfigVo result = chatService.getChatConfig(roomId);
        return ResponseEntity.ok(result);
    }
}
