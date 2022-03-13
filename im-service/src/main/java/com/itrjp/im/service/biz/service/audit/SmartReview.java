package com.itrjp.im.service.biz.service.audit;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.service.entity.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 智能审核
 *
 * @author renjp
 * @date 2022/3/12 15:34
 */
@Slf4j
@Service
public class SmartReview implements MessageAuditHandler {

    @Override
    public boolean match(int type) {
        return type == smart;
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
        log.info("智能审核, message: {}, type: {}", data, type);

        return new AuditResult(AuditStatus.SUCCEEDED, null);
    }
}
