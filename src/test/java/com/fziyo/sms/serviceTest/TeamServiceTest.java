package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.TeamMapper;
import com.fziyo.sms.model.dto.TeamCreateDto;
import com.fziyo.sms.model.entity.Team;
import com.fziyo.sms.model.vo.TeamVo;
import com.fziyo.sms.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.Mockito.verify;

@Slf4j
@SpringBootTest
public class TeamServiceTest {
    @Autowired
    private TeamService teamService;
    
    @MockitoBean
    private TeamMapper teamMapper;
    
    @Test
    public void testSave() {
        TeamCreateDto teamCreateDto = new TeamCreateDto();
        teamCreateDto.setName("Developer");
        teamService.save(teamCreateDto);
        
        ArgumentCaptor<Team> captor = ArgumentCaptor.forClass(Team.class);
        verify(teamMapper).insert(captor.capture());
    }
    
    @Test
    void testDelete() {
        List<Integer> ids = List.of(1,2,3);
        teamService.delete(ids);
        verify(teamMapper).deleteByIds(ids);
    }
    
    @Test
    void testGetAll() {
        List<TeamVo> teamVos = teamService.getAll();
        teamVos.forEach(teamVo -> log.info(teamVo.toString()));
    }
}
