package com.itrjp.im.service.biz.service.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import com.itrjp.im.common.protobuf.KafkaProtobuf;
import com.itrjp.im.service.biz.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 上线
 *
 * @author renjp
 * @date 2022/3/8 22:41
 */
@Service
public class OnLineServiceImpl implements NoticeService<KafkaProtobuf.Online> {

    @Override
    public boolean match(String type) {
        return Objects.equals(type, ONLINE);
    }

    @Override
    public KafkaProtobuf.Online decode(byte[] data) throws InvalidProtocolBufferException {
        return KafkaProtobuf.Online.parseFrom(data);
    }

    @Override
    public void onEvent(KafkaProtobuf.Online data) {
        // 记录当前用户连接的服务

        // 上线广播

    }
}
