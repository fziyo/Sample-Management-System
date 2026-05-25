package com.fziyo.sms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Test
    public void testRedis()
    {
        stringRedisTemplate.opsForValue().set("name", "fu");
        
        String value =
            stringRedisTemplate.opsForValue().get("name");
        
        System.out.println(value);
    }
}
