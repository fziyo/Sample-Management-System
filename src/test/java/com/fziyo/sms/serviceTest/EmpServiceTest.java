package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.EmpMapper;
import com.fziyo.sms.pojo.Emp;
import com.fziyo.sms.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
@SpringBootTest
public class EmpServiceTest {
    @Autowired
    private EmpService empService;
    @MockitoBean
    private EmpMapper empMapper;
    @Test
    public void insertEmp() {
        Emp emp = new Emp();
        emp.setEmpNo("f84382");
        emp.setGender(1);
        emp.setPasswordHash("f84382***");
        emp.setRealName("Fuzi");
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empService.saveEmp(emp);
        verify(empMapper).insert(emp);
    }
}
