package com.fziyo.sms.service.impl;

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
    
    @Override
    public void save(TeamCreateDto teamCreateDto) {
        Team team = new Team();
        BeanUtils.copyProperties(teamCreateDto, team);
        teamMapper.insert(team);
    }
    
    @Override
    public void delete(List<Integer> ids) {
        teamMapper.deleteByIds(ids);
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
