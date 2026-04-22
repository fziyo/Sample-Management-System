package com.fziyo.sms.service.impl;

import com.fziyo.sms.common.constant.ResponseCode;
import com.fziyo.sms.common.exception.BusinessException;
import com.fziyo.sms.mapper.UserMapper;
import com.fziyo.sms.mapper.RoleMapper;
import com.fziyo.sms.model.dto.RoleCreateDto;
import com.fziyo.sms.model.entity.Role;
import com.fziyo.sms.model.vo.RoleVo;
import com.fziyo.sms.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public void save(RoleCreateDto rolecreateDto) {
        Role role = new Role();
        BeanUtils.copyProperties(rolecreateDto,role);
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        if (roleMapper.insert(role) == 0) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Fail to insert role");
        }
        log.info("Save role: {}", role);
    }
    
    @Override
    public void deleteById(Integer id) {
        if (userMapper.countByRoleId(id) > 0) {
            throw new BusinessException(ResponseCode.CONFLICT, "Role has users, cannot delete role");
        }
        roleMapper.deleteById(id);
        log.info("Delete role: {}", id);
    }
    
    @Override
    public List<RoleVo> getAll() {
        List<Role> roles = roleMapper.list();
        if (roles == null || roles.isEmpty()) {
            throw new BusinessException(ResponseCode.NOT_FOUND, "Roles not found");
        }
        log.info("Get roles size: {}", roles.size());
        return roles.stream().map(role -> {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role,roleVo);
            return roleVo;
        }).toList();
    }
}
