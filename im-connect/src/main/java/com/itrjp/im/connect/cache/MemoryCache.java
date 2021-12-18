package com.itrjp.im.connect.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache<K, V> implements Cache<K, V> {
    private final Map<K, V> map = new ConcurrentHashMap<>();

    @Override

    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void set(K key, V val) {
        map.put(key, val);
    }

    @Override
    public boolean hasKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }
}
