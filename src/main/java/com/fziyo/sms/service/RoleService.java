package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.RoleCreateDto;
import com.fziyo.sms.model.entity.Role;

import java.util.List;

public interface RoleService {
    
    void save(RoleCreateDto rolecreateDto);
    
    void delete(List<Integer> ids);
    
}
