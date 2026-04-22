package com.fziyo.sms.mapper;

import com.fziyo.sms.model.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    int deleteByIds(List<Integer> ids);
    
    int update(User user);
    
    User getById(Integer id);
    
    Integer countByTeamId(Integer id);
    
    Integer countByRoleId(Integer id);
    
    List<User> list();
    
    User getByEmpNo(String empNo);
    
}
