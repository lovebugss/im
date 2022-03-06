package com.itrjp.im.api.service.impl;

import com.alibaba.nacos.common.utils.MD5Utils;
import com.itrjp.im.api.pojo.vo.ChatConfigVo;
import com.itrjp.im.api.service.ChatService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/5 11:29
 */
@Service
public class ChatServiceImpl implements ChatService {
    private String token = "123456";


    /**
     * 获取聊天室连接地址
     * <p>
     * 大致流程：
     * <pre>
     *     1. 获取最佳节点
     *
     *     2. 计算签名token
     * </pre>
     *
     * @param roomId
     * @return
     */
    @Override
    public ChatConfigVo getChatConfig(String roomId) {
        // 生成鉴权token

        // 获取最佳节点
        ChatConfigVo chatConfigVo = new ChatConfigVo();
        StringBuilder url = new StringBuilder();
        long time = System.currentTimeMillis();
        String key = MD5Utils.encodeHexString((time + roomId + token).getBytes(StandardCharsets.UTF_8));
        url.append("ws://127.0.0.1:8888/ws?room_id=")
                .append(roomId)
                .append("&t=")
                .append(time)
                .append("&app_id=")
                .append("&k=")
                .append(key)
                .append("&u=").append("zhangsan");
        chatConfigVo.setConnectUrl(url.toString());
        chatConfigVo.setToken(key);
        return chatConfigVo;
    }


}
