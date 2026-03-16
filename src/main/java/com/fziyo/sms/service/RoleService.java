package com.fziyo.sms.service;

import com.fziyo.sms.pojo.Role;

import java.util.List;

public interface RoleService {
    
    void save(Role role);
    
    void delete(List<Integer> ids);
    
}
