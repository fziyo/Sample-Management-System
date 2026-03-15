package com.fziyo.sms.mapper;

import com.fziyo.sms.pojo.Emp;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmpMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("Insert into emp(emp_no, real_name, password_hash, gender, team_id, role_id, create_time, update_time) " +
            "values(#{empNo}, #{realName}, #{passwordHash}, #{gender}, #{teamId}, #{roleId},#{createTime}, #{updateTime})")
    void insert(Emp emp);
    
    
}
