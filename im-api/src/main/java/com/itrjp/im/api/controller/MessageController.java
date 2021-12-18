package com.itrjp.im.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("message")
public class MessageController {

    @GetMapping("history/{channelId}")
    public ResponseEntity<?> queryHistoryMessage(@PathVariable("channelId") String channelId) {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }
}
