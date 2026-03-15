package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.RoleMapper;
import com.fziyo.sms.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;
    
    @Test
    public void insert() {
        Role role = new Role();
        role.setRoleName("Admin");
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insert(role);
    }
}
