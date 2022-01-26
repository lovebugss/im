package com.itrjp.core.util.pubsub;

/**
 * 发布/订阅
 *
 * @author renjp
 * @date 2022/1/16 19:44
 */
public interface PubSub {

    /**
     * 发布
     * @param type
     * @param message
     */
    void publish(PubSubType type, PubSubMessage message);

    /**
     * 订阅
     * @param type
     * @param listener
     * @param clazz
     * @param <T>
     */
    <T extends PubSubMessage> void subscribe(PubSubType type, PubSubListener<T> listener, Class<T> clazz);

    void unsubscribe(PubSubType type);

    void shutdown();
}
