package com.zsq.learnspringboot;

import com.zsq.learnspringboot.pojo.User;
import com.zsq.learnspringboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnSpringBootApplicationTests {


    private  final UserService userService;

    @Autowired
    public LearnSpringBootApplicationTests(UserService userService) {
        this.userService = userService;
    }
    @Test
    void testUserService() {
        User user = userService.getById(991248385);
        System.out.println(user.getUsername());
    }

}
