package com.fziyo.sms.mapper;

import com.fziyo.sms.model.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    
    int insert(Role role);
    
    int deleteById(Integer id);
    
    List<Role> list();
    
}
