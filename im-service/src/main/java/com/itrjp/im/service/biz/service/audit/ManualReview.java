package com.itrjp.im.service.biz.service.audit;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.service.entity.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 人工审核
 *
 * @author renjp
 * @date 2022/3/12 15:31
 */
@Slf4j
@Service
public class ManualReview implements MessageAuditHandler {


    @Override
    public boolean match(int type) {
        return type == manual;
    }

    /**
     * TODO
     *
     * @param data
     * @param type
     * @return
     */
    @Override
    public AuditResult audit(MessageProtobuf.Message data, Room type) {
        log.info("人工审核, message: {}, type: {}", data, type);
        return new AuditResult(AuditStatus.SUCCEEDED, null);
    }
}
