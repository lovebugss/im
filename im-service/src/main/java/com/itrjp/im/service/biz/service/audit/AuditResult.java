package com.itrjp.im.service.biz.service.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 审核结果
 *
 * @author renjp
 * @date 2022/3/12 15:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditResult {
    private AuditStatus status = AuditStatus.FAILED;
    private String id;
}
