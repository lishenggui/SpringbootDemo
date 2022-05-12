package com.seed.auth.exception;

/**
 * Token异常
 *
 * @author lishenggui
 * @since 2018/12/7
 */
public class TokenException extends RuntimeException {
    public TokenException() {
        super("False token");
    }
}
