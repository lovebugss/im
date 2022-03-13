package com.itrjp.im.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static com.itrjp.im.common.conts.DateConstant.DEFAULT_DATETIME_FORMAT;


/**
 * 自定义LocalDateTime解析器 忽略 0000-00-0 00:00:00
 *
 * @author renjp
 * @date 2021/10/18 15:52
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String data = p.getText().trim();
        if (data.length() == 0) {
            return null;
        }
        try {
            return LocalDateTime.parse(data, DEFAULT_DATETIME_FORMAT);

        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
