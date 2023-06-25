package com.seed.user.controller;

import com.seed.auth.annotation.Auth;
import com.seed.base.entity.Result;
import com.seed.user.data.SignInData;
import com.seed.user.data.SignUpData;
import com.seed.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = "用户操作")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登陆接口
     */
    @PostMapping("/signIn")
    @ApiOperation("创建文章")
    public Result signIn(@RequestBody @Validated SignInData data) {
        // 使用SpringValidation校验数据
        return new Result().success(userService.signIn(data));
    }

    /**
     * 注册接口
     */
    @PostMapping("/signUp")
    @ApiOperation("注册接口")
    public Result signUp(@RequestBody @Validated SignUpData data) {
        // 使用SpringValidation校验数据
        userService.signUp(data);
        return new Result().success();
    }

    /**
     * 查看当前用户的Id
     */
    @GetMapping
    @ApiOperation("查看当前用户的Id")
    public Result get(@Auth(required = false) Long userId) {
        // 该接口可以不登陆，未登陆时返回的结果为空，登陆时会返回当前登陆用户的Id
        return new Result().success(userId);
    }

    /**
     * 根据Id查看单个用户的信息
     */
    @GetMapping("/{userId}")
    @ApiOperation("根据Id查看单个用户的信息")
    public Result get(@PathVariable("userId") long userId) {
        return new Result().success(userService.getById(userId));
    }

    /**
     * 查看用户列表
     */
    @GetMapping("/list")
    @ApiOperation("查看用户列表")
    public String list(Model model) {
        model.addAttribute("users",userService.list());
        return "list";
    }
}