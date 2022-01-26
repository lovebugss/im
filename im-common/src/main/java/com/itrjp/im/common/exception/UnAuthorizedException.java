package com.itrjp.im.common.exception;

/**
 * UNAUTHORIZED
 *
 * @author renjp
 * @date 2022/1/22 10:56
 */
public class UnAuthorizedException extends BizException {
    public UnAuthorizedException() {
        super(403, "UNAUTHORIZED");
    }
}
