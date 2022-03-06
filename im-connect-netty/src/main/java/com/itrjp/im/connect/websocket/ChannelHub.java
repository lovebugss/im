package com.itrjp.im.connect.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/6 14:52
 */
public class ChannelHub {

    private final Map<String, Channel> allChannel = new ConcurrentHashMap<>();


}
