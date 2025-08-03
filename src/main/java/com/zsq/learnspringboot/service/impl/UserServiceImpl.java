package com.zsq.learnspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsq.learnspringboot.mapper.UserMapper;
import com.zsq.learnspringboot.pojo.User;
import com.zsq.learnspringboot.service.UserService;
import com.zsq.learnspringboot.utils.Md5Util;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Override
    public void register(User user){
        //对密码进行加密
        user.setPassword(Md5Util.getMD5String(user.getPassword()));

        //设置创建时间
        user.setCreateTime(LocalDateTime.now());
        //设置更新时间
        user.setUpdateTime(LocalDateTime.now());
        //保存用户
        this.save(user);
    }

    @Override
    public User findByUserName(String username){
        //创建查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);

        //查询
        return this.getOne(queryWrapper);
    }
}