package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.UserMapper;
import com.fziyo.sms.model.dto.EmpCreateDto;
import com.fziyo.sms.model.dto.EmpUpdateDto;
import com.fziyo.sms.model.entity.User;
import com.fziyo.sms.service.EmpService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private EmpService empService;
    @MockitoBean
    private UserMapper userMapper;
    @Test
    public void testSave() {
        EmpCreateDto empCreateDto = new EmpCreateDto();
        empCreateDto.setEmpNo("f84382");
        empCreateDto.setGender(1);
        empCreateDto.setName("Fuzi");
        empCreateDto.setTeamId(1);
        empCreateDto.setRoleId(1);
        empService.save(empCreateDto);
        
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userMapper).insert(captor.capture());
        User savedUser = captor.getValue();
        assertEquals("f84382", savedUser.getUsername());
        assertEquals("Fuzi", savedUser.getName());
    }
    
    @Test
    void testDeleteByIds() {
        List<Integer> ids = List.of(1,2,3);
        empService.deleteByIds(ids);
        verify(userMapper).deleteByIds(ids);
    }
    
    @Test
    void testUpdate() {
        EmpUpdateDto empUpdateDto = new EmpUpdateDto();
        empService.update(empUpdateDto);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userMapper).update(captor.capture());
        
    }
    
    @Test
    void testGetById() {
        User user = new User();
        user.setId(1);
        
        when(userMapper.getById(1)).thenReturn(user);
        
        empService.getById(1);
        
        verify(userMapper).getById(1);
    }
    
    @Test
    void testGetAll() {
        empService.getAll();
        verify(userMapper).list();
    }
}
