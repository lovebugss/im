package com.itrjp.im.service.biz.service.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import com.itrjp.im.common.protobuf.KafkaProtobuf;
import com.itrjp.im.service.biz.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 下线
 *
 * @author renjp
 * @date 2022/3/8 22:49
 */
@Slf4j
@Service
public class OffLineServiceImpl implements NoticeService<KafkaProtobuf.OffLine> {
    @Override
    public boolean match(String type) {
        return Objects.equals(type, OFFLINE);
    }

    @Override
    public KafkaProtobuf.OffLine decode(byte[] data) throws InvalidProtocolBufferException {

        return KafkaProtobuf.OffLine.parseFrom(data);
    }

    @Override
    public void onEvent(KafkaProtobuf.OffLine data) {
        log.info("offline data: {}", data);
    }

}
