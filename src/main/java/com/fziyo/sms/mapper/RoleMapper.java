package com.fziyo.sms.mapper;

import com.fziyo.sms.model.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("Insert into role(name, create_time, update_time) " +
                    "values(#{name}, #{createTime}, #{updateTime})")
    int insert(Role role);
    
    int deleteByIds(List<Integer> ids);
    
    @Select("Select * from role")
    List<Role> list();
    
}
