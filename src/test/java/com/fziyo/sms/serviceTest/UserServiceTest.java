package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.UserMapper;
import com.fziyo.sms.model.dto.UserCreateDto;
import com.fziyo.sms.model.dto.UserUpdateDto;
import com.fziyo.sms.model.entity.User;
import com.fziyo.sms.service.UserService;
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
    private UserService userService;
    @MockitoBean
    private UserMapper userMapper;
    @Test
    public void testSave() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setEmpNo("f84382");
        userCreateDto.setGender(1);
        userCreateDto.setName("Fuzi");
        userCreateDto.setTeamId(1);
        userCreateDto.setRoleId(1);
        userService.save(userCreateDto);
        
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userMapper).insert(captor.capture());
        User savedUser = captor.getValue();
        assertEquals("f84382", savedUser.getUsername());
        assertEquals("Fuzi", savedUser.getName());
    }
    
    @Test
    void testDeleteByIds() {
        List<Integer> ids = List.of(1,2,3);
        userService.deleteByIds(ids);
        verify(userMapper).deleteByIds(ids);
    }
    
    @Test
    void testUpdate() {
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userService.update(userUpdateDto);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userMapper).update(captor.capture());
        
    }
    
    @Test
    void testGetById() {
        User user = new User();
        user.setId(1);
        
        when(userMapper.getById(1)).thenReturn(user);
        
        userService.getById(1);
        
        verify(userMapper).getById(1);
    }
    
    @Test
    void testGetAll() {
        userService.getAll();
        verify(userMapper).list();
    }
}
