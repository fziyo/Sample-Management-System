package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.RoleCreateDto;
import com.fziyo.sms.model.entity.Role;
import com.fziyo.sms.model.vo.RoleVo;

import java.util.List;

public interface RoleService {
    
    void save(RoleCreateDto rolecreateDto);
    
    void deleteById(Integer id);
    
    List<RoleVo> getAll();
}
