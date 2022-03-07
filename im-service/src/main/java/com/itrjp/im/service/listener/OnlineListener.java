package com.itrjp.im.service.listener;

/**
 * 上线
 * TODO 是否可以合并? 合并成通知
 *
 * @author renjp
 * @date 2022/3/7 23:47
 */
public interface OnlineListener {
    /**
     * 上线
     *
     * @param data
     */
    void onOnline(byte[] data);
}
