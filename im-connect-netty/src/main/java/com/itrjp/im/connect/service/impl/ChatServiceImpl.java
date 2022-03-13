package com.itrjp.im.connect.service.impl;

import com.itrjp.im.common.conts.KafkaConstant;
import com.itrjp.im.common.dto.OnlineOfflineDTO;
import com.itrjp.im.common.dubbo.service.StatusService;
import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.connect.config.WebSocketProperties;
import com.itrjp.im.connect.service.ChatService;
import com.itrjp.im.connect.service.TokenService;
import com.itrjp.im.connect.websocket.HandshakeData;
import com.itrjp.im.connect.websocket.RequestParam;
import com.itrjp.im.connect.websocket.WebSocketClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 聊天实现
 *
 * @author renjp
 * @date 2022/3/5 23:48
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final TokenService tokenService;
    private final WebSocketProperties webSocketProperties;

    @DubboReference
    private StatusService statusService;


    @Override
    public void onMessage(WebSocketClient client, MessageProtobuf.Message message) {
        // 当前用户, 收件人, 消息内容, 时间戳,
        MessageProtobuf.Message c_01234567 = message.toBuilder().setRoomId("r_12345678").build();
        // 收到消息后， 直接通过kafka 找给业务层
        kafkaTemplate.send(KafkaConstant.TOPIC_MESSAGE, c_01234567.toByteArray());
    }

    @Override
    public void onConnect(WebSocketClient client) {
        HandshakeData handshakeData = client.getHandshakeData();
        log.info("用户上线");
        String session = client.getSessionId();
        CompletableFuture<Void> completableFuture = statusService.onlineAsync(new OnlineOfflineDTO());
        completableFuture.whenComplete((r, e) -> {
            if (e != null) {
                log.error("上线统计失败", e);
                // TODO
                return;
            }

        });
    }


    @Override
    public void onDisconnect(WebSocketClient client) {

    }

    @Override
    public void onException(WebSocketClient client, Throwable exception) {

    }

    @Override
    public boolean isAuthorize(HandshakeData data) {
        RequestParam param = data.getParam();
        String roomId = param.getRoomId();

        return roomId != null && !roomId.isEmpty()
                && checkToken(param)
                && checkRoomLimit(roomId);
    }

    /**
     * 检查是否到达房间限制
     *
     * @param roomId
     * @return
     */
    private boolean checkRoomLimit(String roomId) {
        int roomPV = statusService.getRoomPV(roomId);
        log.info("current room pv: {}", roomPV);
        return true;
    }

    /**
     * 检查token
     *
     * @param urlParams
     * @return
     */
    private boolean checkToken(RequestParam urlParams) {
        return tokenService.check(urlParams);
    }
}
