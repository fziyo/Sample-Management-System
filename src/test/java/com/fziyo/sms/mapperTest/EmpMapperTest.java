package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.EmpMapper;
import com.fziyo.sms.model.entity.Emp;
import com.fziyo.sms.model.vo.EmpVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
public class EmpMapperTest {
    @Autowired
    private EmpMapper empMapper;

    @Test
    void testInert() {
        Emp emp = new Emp();
        emp.setEmpNo("f1234");
        emp.setPwd("123456");
        emp.setName("Fuzi");
        emp.setEmail("fuzi@qq.com");
        emp.setGender(1);
        emp.setCreateTime(LocalDateTime.now());
        emp.setEditTime(LocalDateTime.now());
        assert(empMapper.insert(emp) == 1);
    }

    @Test
    void testDeleteByIds() {
        List<Integer> ids = List.of(100000);
        assert(empMapper.deleteByIds(ids) == 1);
    }
    
    @Test
    void testUpdate() {
        Emp emp = new Emp();
        emp.setId(100001);
        emp.setEmpNo("M1234");
        emp.setGender(2);
        emp.setName("Mary");
        emp.setEditTime(LocalDateTime.now());
        assert(empMapper.update(emp) == 1);
    }
    
    @Test
    void testGetById() {
        Emp emp = empMapper.getById(100001);
        if (emp != null) {
            log.info(emp.toString());
        }

    }
    
    @Test
    void testList() {
        List<Emp> emps = empMapper.list();
        log.info(emps.toString());
    }
}
