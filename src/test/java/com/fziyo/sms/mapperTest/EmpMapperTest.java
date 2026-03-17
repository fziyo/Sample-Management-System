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
        emp.setGender(1);
        emp.setName("Fuzi");
        emp.setTeamId(1);
        emp.setRoleId(1);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        assert(empMapper.insert(emp) == 1);
    }

    @Test
    void testDeleteByIds() {
        List<Integer> ids = List.of(1);
        assert(empMapper.deleteByIds(ids) == 1);
    }
    
    @Test
    void testUpdate() {
        Emp emp = new Emp();
        emp.setId(4);
        emp.setEmpNo("M1234");
        emp.setGender(2);
        emp.setName("Mary");
        emp.setTeamId(2);
        emp.setRoleId(2);
        emp.setUpdateTime(LocalDateTime.now());
        assert(empMapper.update(emp) == 0);
    }
    
    @Test
    void testGetById() {
        EmpVo empVo = empMapper.getById(4);
        if (empVo != null) {
            log.info(empVo.toString());
        }

    }
    
    @Test
    void testList() {
        List<EmpVo> empVos = empMapper.list();
        log.info(empVos.toString());
    }
}
