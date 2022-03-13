package com.itrjp.im.service.biz.service.audit;

import com.itrjp.im.common.protobuf.MessageProtobuf;
import com.itrjp.im.service.entity.Room;

/**
 * 消息审计
 *
 * @author renjp
 * @date 2022/3/12 14:03
 */
public interface MessageAuditHandler {
    int manual = 1;
    int smart = 2;
    int not = 3;
    int d = 0;

    boolean match(int type);

    /**
     * 消息审核
     * <pre>
     *     1. 人工审核
     *     2. 智能消息过滤
     *     3. 不审核
     * </pre>
     *
     * @param data
     * @param type
     * @return
     */
    AuditResult audit(MessageProtobuf.Message data, Room type);
}
