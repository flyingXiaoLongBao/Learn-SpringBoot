package com.zsq.learnspringboot.controller;


import com.zsq.learnspringboot.pojo.Result;
import com.zsq.learnspringboot.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final JwtUtil jwtUtil;

    @Autowired
    public ArticleController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/list")
    public Result<String> list(@RequestHeader(name = "Authorization") String token) {
        // 解析token获取用户信息
        try {
            Map<String, Object> claims = jwtUtil.parseToken(token);
            Integer id = (Integer) claims.get("id");
            String username = (String) claims.get("username");
            System.out.println("当前用户ID: " + id + ", 用户名: " + username);
        } catch (Exception e) {
            return Result.error("令牌无效");
        }
        
        return Result.success("...所有文章的数据...");
    }
}