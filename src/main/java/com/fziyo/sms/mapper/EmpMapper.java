package com.fziyo.sms.mapper;

import com.fziyo.sms.model.entity.Emp;
import com.fziyo.sms.model.vo.EmpVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("Insert into emp(emp_no, name, gender, team_id, role_id) " +
            "values(#{empNo}, #{name}, #{gender}, #{teamId}, #{roleId})")
    int insert(Emp emp);

    int deleteByIds(List<Integer> ids);
    
    int update(Emp emp);
    
    Emp getById(int id);
    
    List<Emp> list();
    
}
