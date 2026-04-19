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
        role.setRoleName("Administrator");
        role.setRoleCode("ADMIN");
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        assert(roleMapper.insert(role) == 1);
    }
    @Test
    public void deleteByIds() {
        assert(roleMapper.deleteById(2) == 0);
    }
    
    @Test
    public void testList() {
        List<Role> roles = roleMapper.list();
        log.info(roles.toString());
    }
}
