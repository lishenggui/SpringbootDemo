package com.seed.user.exception;

import com.seed.base.exception.ServiceException;

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super(10404, "用户不存在");
    }
}