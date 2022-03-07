package com.itrjp.im.service.listener;

/**
 * 下线
 * TODO 是否可以合并?
 *
 * @author renjp
 * @date 2022/3/7 23:47
 */
public interface OfflineListener {
    /**
     * 下线
     *
     * @param data
     */
    void onOffline(byte[] data);
}
