package com.zsq.learnspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsq.learnspringboot.pojo.User;

public interface UserService extends IService<User> {

    void register(User user);

    User findByUserName(String username);
}
