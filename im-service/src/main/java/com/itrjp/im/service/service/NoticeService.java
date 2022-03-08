package com.itrjp.im.service.service;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/8 22:42
 */
public interface NoticeService {
    String ONLINE = "online";
    String OFFLINE = "online";

    boolean match(String type);

    void onEvent(byte[] data);
}
