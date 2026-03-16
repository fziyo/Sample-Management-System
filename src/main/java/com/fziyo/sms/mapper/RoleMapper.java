package com.fziyo.sms.mapper;

import com.fziyo.sms.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("Insert into role(role_name, create_time, update_time) " +
                    "values(#{roleName}, #{createTime}, #{updateTime})")
    void insert(Role role);
    
    void deleteByIds(List<Integer> ids);
    
}
