package com;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashmapTest {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        String s = map.putIfAbsent("1", "1");
        System.out.println(s);
        String s1 = map.putIfAbsent("1", "2");
        System.out.println(s1);
        System.out.println(map);
    }
}
