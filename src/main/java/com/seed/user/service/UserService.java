package com.seed.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seed.user.data.SignInData;
import com.seed.user.data.SignUpData;
import com.seed.user.entity.User;
import com.seed.user.view.SignInView;

public interface UserService extends IService<User> {
    SignInView signIn(SignInData data);
    void signUp(SignUpData data);
}
