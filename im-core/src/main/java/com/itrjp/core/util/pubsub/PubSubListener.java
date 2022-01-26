package com.itrjp.core.util.pubsub;

/**
 * 监听器
 *
 * @author renjp
 * @date 2022/1/16 19:51
 */
public interface PubSubListener<T> {
    void onMessage(T data);
}
