package com.itrjp.core.util.json;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/1/16 18:40
 */
public interface JsonDe {
    String serialization(Object obj);

    <T> T deserialize(String data, Class<T> clazz);
}
