package com.itrjp.im.connect.cache;

import com.corundumstudio.socketio.SocketIONamespace;

import java.util.function.Function;

public interface Cache<K, V> {
    V get(K key);

    void set(K key, V val);

    boolean hasKey(K key);

    void remove(K key);

    void putIfAbsent(String channel, Function<String, SocketIONamespace> function);
}
