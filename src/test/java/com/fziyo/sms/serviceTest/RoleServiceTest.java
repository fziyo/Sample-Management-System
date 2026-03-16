package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.RoleMapper;
import com.fziyo.sms.model.entity.Role;
import com.fziyo.sms.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    @MockitoBean
    private RoleMapper roleMapper;
    @Test
    void saveTest() {
        Role role = new Role();
        roleService.save(role);
        verify(roleMapper).insert(role);
    }
    
}
