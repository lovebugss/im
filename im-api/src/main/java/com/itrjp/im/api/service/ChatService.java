package com.itrjp.im.api.service;

import com.itrjp.im.api.pojo.vo.ChatConfigVo;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/5 11:29
 */
public interface ChatService {
    ChatConfigVo getChatConfig(String roomId);

}
