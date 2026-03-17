package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.RoleMapper;
import com.fziyo.sms.model.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    DataSource dataSource;
    
    @Test
    void printDB() throws Exception {
        log.info("DB URL = {}", dataSource.getConnection().getMetaData().getURL());
    }
    @Test
    public void insert() {
        Role role = new Role();
        role.setName("Admin");
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        assert(roleMapper.insert(role) == 1);
    }
    @Test
    public void deleteByIds() {
        List<Integer> ids = List.of(2);
        assert(roleMapper.deleteByIds(ids) == 0);
    }
    
    @Test
    public void testList() {
        List<Role> roles = roleMapper.list();
        log.info(roles.toString());
    }
}
