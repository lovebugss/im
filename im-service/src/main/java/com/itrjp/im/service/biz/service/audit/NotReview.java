package com.itrjp.im.service.biz.service.audit;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.service.entity.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author renjp
 * @date 2022/3/12 15:39
 */
@Slf4j
@Service
public class NotReview implements MessageAuditHandler {
    @Override
    public boolean match(int type) {
        return type == not;
    }

    @Override
    public AuditResult audit(MessageProtobuf.Message data, Room type) {
        log.info("不审核, message: {}, type: {}", data, type);
        return new AuditResult(AuditStatus.SUCCEEDED, null);
    }
}
