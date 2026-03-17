package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.RoleMapper;
import com.fziyo.sms.model.dto.RoleCreateDto;
import com.fziyo.sms.model.entity.Role;
import com.fziyo.sms.model.vo.RoleVo;
import com.fziyo.sms.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@Slf4j
@SpringBootTest
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    @MockitoBean
    private RoleMapper roleMapper;
    @Autowired
    DataSource dataSource;
    
    @Test
    void printDB() throws Exception {
        log.info("DB URL = {}", dataSource.getConnection().getMetaData().getURL());
    }
    
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
    
    @Test
    void testGetAll() {
        List<RoleVo> roleVos = roleService.getAll();
        verify(roleMapper).list();
        
    }
    
}
