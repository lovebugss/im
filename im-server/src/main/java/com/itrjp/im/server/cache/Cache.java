package com.itrjp.im.server.cache;

public interface Cache<K, V> {
    V get(K key);

    void set(K key, V val);

    boolean hasKey(K key);

    void remove(K key);
}
