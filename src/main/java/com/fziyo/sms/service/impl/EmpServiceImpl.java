package com.fziyo.sms.service.impl;

import com.fziyo.sms.common.constant.ResponseCode;
import com.fziyo.sms.common.exception.BusinessException;
import com.fziyo.sms.mapper.UserMapper;
import com.fziyo.sms.model.dto.EmpCreateDto;
import com.fziyo.sms.model.dto.EmpUpdateDto;
import com.fziyo.sms.model.entity.User;
import com.fziyo.sms.model.vo.UserVo;
import com.fziyo.sms.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(EmpCreateDto empcreatedto) {
        User user = new User();
        BeanUtils.copyProperties(empcreatedto, user);
        if (userMapper.insert(user) == 0) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to save emp");
        }
        log.info("Saved employee, emp: {}", user);
    }
    
    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        if (userMapper.deleteByIds(ids) != ids.size()) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to delete employees");
        }
        log.info("Deleted employees, ids: {}", ids);
    }
    
    @Override
    public void update(EmpUpdateDto empUpdateDto) {
        User user = new User();
        BeanUtils.copyProperties(empUpdateDto, user);
        if (userMapper.update(user) == 0) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to update employees");
        }
        log.info("Updated employee, emp: {}", user);
    }
    
    @Override
    public UserVo getById(Integer id) {
        User user = userMapper.getById(id);
        if (user == null) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "User not found");
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        log.info("Get employee, emp: {}", userVo);
        return userVo;
    }
    
    @Override
    public List<UserVo> getAll() {
        List<User> users = userMapper.list();
        if (users == null) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Users not found");
        }
        log.info("Get employees, emps size: {}", users.size());
        return users.stream().map(emp -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(emp, userVo);
            return userVo;
        }).toList();
    }
}
