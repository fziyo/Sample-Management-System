package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.EmpMapper;
import com.fziyo.sms.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class EmpMapperTest {
    @Autowired
    private EmpMapper empMapper;

    @Test
    void testInertEmp() {
        Emp emp = new Emp();
        emp.setEmpNo("z1234");
        emp.setGender(1);
        emp.setPasswordHash("z1234***");
        emp.setRealName("Mary");
        emp.setTeamId(1);
        emp.setRoleId(1);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }
}
