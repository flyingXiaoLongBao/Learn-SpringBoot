package com.zsq.learnspringboot;

import com.zsq.learnspringboot.pojo.User;
import com.zsq.learnspringboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class LearnSpringBootApplicationTests {

    private final RedisTemplate redisTemplate;

    @Autowired
    public LearnSpringBootApplicationTests(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Test
    void testRedisSet() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name", "zsq");
    }
}
