package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.UserMapper;
import com.fziyo.sms.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testInert() {
        User user = new User();
        user.setUsername("f1234");
        user.setPwd("123456");
        user.setName("Fuzi");
        user.setEmail("fuzi@qq.com");
        user.setGender(1);
        user.setCreateTime(LocalDateTime.now());
        user.setEditTime(LocalDateTime.now());
        assert(userMapper.insert(user) == 1);
    }

    @Test
    void testDeleteByIds() {
        List<Integer> ids = List.of(100000);
        assert(userMapper.deleteByIds(ids) == 1);
    }
    
    @Test
    void testUpdate() {
        User user = new User();
        user.setId(100001);
        user.setUsername("M1234");
        user.setGender(2);
        user.setName("Mary");
        user.setEditTime(LocalDateTime.now());
        assert(userMapper.update(user) == 1);
    }
    
    @Test
    void testGetById() {
        User user = userMapper.getById(100001);
        if (user != null) {
            log.info(user.toString());
        }

    }
    
    @Test
    void testList() {
        List<User> users = userMapper.list();
        log.info(users.toString());
    }
}
