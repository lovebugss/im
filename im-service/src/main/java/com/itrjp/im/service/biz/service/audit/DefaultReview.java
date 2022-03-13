package com.itrjp.im.service.biz.service.audit;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.service.entity.Room;
import org.springframework.stereotype.Service;

/**
 * 默认审核, 过滤黑词
 *
 * @author renjp
 * @date 2022/3/12 23:37
 */
@Service
public class DefaultReview implements MessageAuditHandler {
    @Override
    public boolean match(int type) {
        return type == 0;
    }

    @Override
    public AuditResult audit(MessageProtobuf.Message data, Room type) {
        return new AuditResult(AuditStatus.SUCCEEDED, null);
    }
}
