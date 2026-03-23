package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.TeamMapper;
import com.fziyo.sms.model.entity.Team;
import com.fziyo.sms.model.vo.TeamVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class TeamMapperTest {
    @Autowired
    private TeamMapper teamMapper;
    
    @Test
    void testInsert() {
        Team team = new Team();
        team.setName("HR");
        teamMapper.insert(team);
    }
    
    @Test
    void testDeleteById() {
        teamMapper.deleteById(1);
    }
    
    @Test
    void testList() {
        List<Team> teamVos = teamMapper.list();
        teamVos.forEach(team -> log.info(team.toString()));
    }
}
