package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.RoleMapper;
import com.fziyo.sms.model.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;
    
    @Test
    public void insert() {
        Role role = new Role();
        role.setName("Admin");
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insert(role);
    }
    @Test
    public void deleteByIds() {
        List<Integer> ids = List.of(2);
        roleMapper.deleteByIds(ids);
    }
}
