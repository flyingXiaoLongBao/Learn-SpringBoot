package com.zsq.learnspringboot.interceptors;

import com.zsq.learnspringboot.utils.JwtUtil;
import com.zsq.learnspringboot.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;


@Component
public class LoginInterceptor implements HandlerInterceptor {

    private JwtUtil jwtUtil;

    @Autowired
    public LoginInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        
        // 添加日志以便诊断问题
        System.out.println("拦截器捕获到请求: " + request.getRequestURI());
        System.out.println("Authorization头信息: " + token);
        
        // 检查token是否存在
        if (token == null || token.isEmpty()) {
            System.out.println("未提供Authorization头信息");
            response.setStatus(401);
            return false;
        }
        
        try {
            Map<String, Object> map = jwtUtil.parseToken(token);
            System.out.println("Token解析成功: " + map);
            //把相关信息存储到threadLocal中
            ThreadLocalUtil.set(map);
            //放行
            return true;
        }
        catch (Exception e) {
            System.out.println("Token解析失败: " + e.getMessage());
            response.setStatus(401);
            //不放行
            return false;
        }
    }
}