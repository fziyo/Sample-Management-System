package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.EmpMapper;
import com.fziyo.sms.mapper.RoleMapper;
import com.fziyo.sms.model.dto.RoleCreateDto;
import com.fziyo.sms.model.entity.Emp;
import com.fziyo.sms.model.entity.Role;
import com.fziyo.sms.service.RoleService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    @MockitoBean
    private RoleMapper roleMapper;
    @Test
    void testSave() {
        RoleCreateDto roleCreateDto = new RoleCreateDto();
        roleCreateDto.setName("Staff");
        roleService.save(roleCreateDto);
        
        ArgumentCaptor<Role> captor = ArgumentCaptor.forClass(Role.class);
        verify(roleMapper).insert(captor.capture());
    }
    
    @Test
    void testDelete() {
        List<Integer> ids = List.of(1,2,3);
        roleService.delete(ids);
        verify(roleMapper).deleteByIds(ids);
    
    }
    
}
