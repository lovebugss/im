package com.itrjp.im.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.itrjp.im.common.conts.DateConstant;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author renjp
 * @date 2022/3/12 23:18
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {


    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String format = value.format(DateConstant.DEFAULT_DATETIME_FORMAT);
        gen.writeString(format);
    }
}
