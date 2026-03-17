package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.TeamCreateDto;
import com.fziyo.sms.model.vo.TeamVo;

import java.util.List;

public interface TeamService {
    void save(TeamCreateDto teamCreateDto);
    
    void delete(List<Integer> ids);
    
    List<TeamVo> getAll();
    
}
