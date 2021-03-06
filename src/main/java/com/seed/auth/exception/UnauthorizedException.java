package com.seed.auth.exception;

import com.seed.base.exception.ServiceException;

/**
 * 未认证异常
 *
 * @author lishenggui
 * @since 2018/12/7
 */
public class UnauthorizedException extends ServiceException {
    public UnauthorizedException() {
        super("Unauthorized", 401);
    }
}
