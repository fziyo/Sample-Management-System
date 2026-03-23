package com.fziyo.sms.service.impl;

import com.fziyo.sms.common.exception.BusinessException;
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
        Emp emp = empMapper.getById(id);
        if (emp == null) {
            throw new BusinessException("User not found");
        }
        EmpVo empVo = new EmpVo();
        BeanUtils.copyProperties(emp,  empVo);
        return empVo;
    }
    
    @Override
    public List<EmpVo> getAll() {
        List<Emp> emps = empMapper.list();
        if (emps == null) {
            throw new BusinessException("Users not found");
        }
        return emps.stream().map(emp -> {
            EmpVo empVo = new EmpVo();
            BeanUtils.copyProperties(emp, empVo);
            return empVo;
        }).toList();
    }
}
