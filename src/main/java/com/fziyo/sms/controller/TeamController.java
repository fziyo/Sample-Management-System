package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.TeamCreateDto;
import com.fziyo.sms.model.vo.TeamVo;
import com.fziyo.sms.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/team")
@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;
    
    @PreAuthorize(value = "hasAuthority('team:add)")
    @PostMapping
    public Result<Void> add(@RequestBody TeamCreateDto teamCreateDto) {
        teamService.save(teamCreateDto);
        log.info("Team created successfully {}", teamCreateDto);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('team:delete)")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        teamService.deleteById(id);
        log.info("Team deleted successfully {}", id);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('team:view)")
    @GetMapping
    public Result<List<TeamVo>> list() {
        List<TeamVo> teamVos = teamService.getAll();
        log.info("Teams get successfully {}", teamVos);
        return Result.success(teamVos);
    }
}
