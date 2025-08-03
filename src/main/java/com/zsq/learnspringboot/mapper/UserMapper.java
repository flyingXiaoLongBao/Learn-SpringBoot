package com.zsq.learnspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsq.learnspringboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {

}
