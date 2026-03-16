package com.fziyo.sms.service.impl;

import com.fziyo.sms.mapper.EmpMapper;
import com.fziyo.sms.model.dto.EmpCreateDto;
import com.fziyo.sms.model.dto.EmpUpdateDto;
import com.fziyo.sms.model.entity.Emp;
import com.fziyo.sms.model.vo.EmpVo;
import com.fziyo.sms.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public void save(EmpCreateDto empcreatedto) {
        Emp emp = new Emp();
        BeanUtils.copyProperties(empcreatedto, emp);
   
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        log.info("Added employee, emp: {}", emp);
    }
    
    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);
    }
    
    @Override
    public void update(EmpUpdateDto empUpdateDto) {
        Emp emp = new Emp();
        BeanUtils.copyProperties(empUpdateDto, emp);
        empMapper.update(emp);
    }
    
    @Override
    public EmpVo getById(Integer id) {
        return empMapper.getById(id);
    }
    
    @Override
    public List<EmpVo> getAll() {
        return empMapper.list();
    }
}
