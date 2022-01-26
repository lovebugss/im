package com.itrjp.core.util.pubsub;

import java.util.Locale;

/**
 * 发布类型
 *
 * @author renjp
 * @date 2022/1/16 19:45
 */
public enum PubSubType {
    SYNC, DELETE;

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }
}
