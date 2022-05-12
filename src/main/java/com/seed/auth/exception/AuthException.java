package com.seed.auth.exception;

import com.seed.base.exception.ServiceException;

/**
 * 认证异常
 *
 * @author lishenggui
 * @since 2018/12/7
 */
public class AuthException extends ServiceException {
    public AuthException() {
        super("Authentication failed", 403);
    }
}
