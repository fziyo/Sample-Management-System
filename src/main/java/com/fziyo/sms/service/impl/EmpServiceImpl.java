package com.fziyo.sms.service.impl;

import com.fziyo.sms.mapper.EmpMapper;
import com.fziyo.sms.pojo.Emp;
import com.fziyo.sms.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public void saveEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        log.info("Added employee, emp: {}", emp);
    }
}
