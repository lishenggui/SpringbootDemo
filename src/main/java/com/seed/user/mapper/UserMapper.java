package com.seed.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seed.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}