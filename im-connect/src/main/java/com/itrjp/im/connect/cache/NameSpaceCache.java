package com.itrjp.im.connect.cache;

import com.corundumstudio.socketio.SocketIONamespace;
import com.google.common.cache.CacheBuilder;

/**
 * SocketIONamespace 缓存类
 *
 * @author renjp
 */
public class NameSpaceCache implements Cache<String, SocketIONamespace> {
    private final static NameSpaceCache nameSpaceCache = new NameSpaceCache();
    private final com.google.common.cache.Cache<String, SocketIONamespace> cache;

    private NameSpaceCache() {
        this.cache = CacheBuilder
                .newBuilder()
                .build();
    }

    public static NameSpaceCache getInstance() {
        return nameSpaceCache;
    }

    @Override
    public SocketIONamespace get(String key) {
        return cache.getIfPresent(key);
    }

    @Override
    public void set(String key, SocketIONamespace val) {
        cache.put(key, val);
    }

    @Override
    public boolean hasKey(String key) {
        return get(key) != null;
    }

    @Override
    public void remove(String key) {
        cache.invalidate(key);
    }
}
