package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.EmpCreateDto;
import com.fziyo.sms.model.dto.EmpUpdateDto;
import com.fziyo.sms.model.entity.Emp;
import com.fziyo.sms.model.vo.EmpVo;

import java.util.List;

public interface EmpService {

    void save(EmpCreateDto empcreatedto);
    
    void deleteByIds(List<Integer> ids);
    
    void update(EmpUpdateDto empupdatedto);
    
    EmpVo getById(Integer id);

    List<EmpVo> getAll();
}
