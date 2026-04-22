package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.UserCreateDto;
import com.fziyo.sms.model.dto.UserUpdateDto;
import com.fziyo.sms.model.vo.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(UserCreateDto userCreateDto);
    
    void deleteByIds(List<Integer> ids);
    
    void update(UserUpdateDto userUpdateDto);
    
    UserVo getById(Integer id);
    
    List<UserVo> getAll();
}
