package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.EmpCreateDto;
import com.fziyo.sms.model.dto.EmpUpdateDto;
import com.fziyo.sms.model.vo.UserVo;

import java.util.List;

public interface EmpService {

    void save(EmpCreateDto empcreatedto);
    
    void deleteByIds(List<Integer> ids);
    
    void update(EmpUpdateDto empupdatedto);
    
    UserVo getById(Integer id);

    List<UserVo> getAll();
}
