package com.fziyo.sms.service.impl;

import com.fziyo.sms.common.exception.BusinessException;
import com.fziyo.sms.mapper.EmpMapper;
import com.fziyo.sms.mapper.TeamMapper;
import com.fziyo.sms.model.dto.TeamCreateDto;
import com.fziyo.sms.model.entity.Team;
import com.fziyo.sms.model.vo.TeamVo;
import com.fziyo.sms.service.TeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private EmpMapper empMapper;
    
    @Override
    public void save(TeamCreateDto teamCreateDto) {
        Team team = new Team();
        BeanUtils.copyProperties(teamCreateDto, team);
        teamMapper.insert(team);
    }
    
    @Override
    public void deleteById(Integer id) {
        Integer count = empMapper.countByTeamId(id);
        if (count > 0) {
            throw new BusinessException("More than 1 emp, cannot delete team");
        }
        teamMapper.deleteById(id);
    }
    
    @Override
    public List<TeamVo> getAll() {
        List<Team> teams = teamMapper.list();
        return teams.stream().map(team -> {
            TeamVo teamVo = new TeamVo();
            BeanUtils.copyProperties(team, teamVo);
            return teamVo;
        }).toList();
    }
}
