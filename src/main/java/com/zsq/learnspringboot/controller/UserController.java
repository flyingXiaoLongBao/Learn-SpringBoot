package com.zsq.learnspringboot.controller;


import com.zsq.learnspringboot.pojo.Result;
import com.zsq.learnspringboot.pojo.User;
import com.zsq.learnspringboot.service.UserService;
import com.zsq.learnspringboot.utils.JwtUtil;
import com.zsq.learnspringboot.utils.Md5Util;
import com.zsq.learnspringboot.utils.ThreadLocalUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /*
     * 用户 注册
     * */
    @PostMapping("/register")
    public Result register(@Valid User user) {
        User u = userService.findByUserName(user.getUsername());
        if (u == null) {
            userService.register(user);
            return Result.success();
        } else {
            return Result.error("用户名已存在");
        }
    }

    /*
    * 用户 登录
    * */
    @PostMapping("/login")
    public Result login(@Valid User user) {
        User u = userService.findByUserName(user.getUsername());
        if (u != null) {
            if (u.getPassword().equals(Md5Util.getMD5String(user.getPassword()))) {
                //登录成功，下发JWT令牌
                Map<String, Object> claims = new HashMap<>();

                claims.put("id", u.getId());
                claims.put("username", u.getUsername());

                String token = jwtUtil.genToken(claims);

                return Result.success(token);
            } else {
                return Result.error("密码错误");
            }
        } else {
            return Result.error("用户不存在");
        }
    }

    /*
    * 用户 获取用户详细信息
    * */
    @GetMapping("/userInfo")
    public Result<User> getUserInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        User user = userService.getById(id);
        return Result.success(user);
    }
}
