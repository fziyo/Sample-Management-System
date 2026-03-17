package com.fziyo.sms.service.impl;

import com.fziyo.sms.mapper.RoleMapper;
import com.fziyo.sms.model.dto.RoleCreateDto;
import com.fziyo.sms.model.entity.Role;
import com.fziyo.sms.model.vo.RoleVo;
import com.fziyo.sms.service.RoleService;
import jakarta.servlet.annotation.ServletSecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public void save(RoleCreateDto rolecreateDto) {
        Role role = new Role();
        BeanUtils.copyProperties(rolecreateDto,role);
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insert(role);
    }
    
    @Override
    public void delete(List<Integer> ids) {
        
        roleMapper.deleteByIds(ids);
    }
    
    @Override
    public List<RoleVo> getAll() {
        List<Role> roles = roleMapper.list();
        List<RoleVo> roleVoList = new ArrayList<>();
        for (Role role : roles) {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role,roleVo);
            roleVoList.add(roleVo);
        }
        return roleVoList;
    }
}
