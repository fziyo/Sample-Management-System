package com.fziyo.sms.service.impl;

import com.fziyo.sms.mapper.UserMapper;
import com.fziyo.sms.model.entity.User;
import com.fziyo.sms.service.UerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UerServiceImpl implements UerService {
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getByEmpNo(username);
        
        return null;
    }
}
