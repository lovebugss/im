package com.itrjp.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * json 工具类
 *
 * @author renjp
 * @date 2022/1/16 18:39
 */
@Slf4j
public class JsonUtils {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.warn("json serialize failed, data: {}", data);
        }
        return "";
    }

    public static byte[] serializeToBytes(Object data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            log.warn("json serialize failed, data: [{}]", data);
        }
        return new byte[0];
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.warn("data parse failed, data: [{}], class: [{}]", json, clazz);
            return null;
        }
    }
}
