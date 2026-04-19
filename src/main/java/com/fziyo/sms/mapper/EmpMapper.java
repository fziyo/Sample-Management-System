package com.fziyo.sms.mapper;

import com.fziyo.sms.model.entity.Emp;
import com.fziyo.sms.model.vo.EmpVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Emp emp);

    int deleteByIds(List<Integer> ids);
    
    int update(Emp emp);
    
    Emp getById(Integer id);
    
    Integer countByTeamId(Integer id);
    
    Integer countByRoleId(Integer id);
    
    List<Emp> list();
    
}
