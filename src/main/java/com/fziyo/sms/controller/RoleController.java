package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.RoleCreateDto;
import com.fziyo.sms.model.vo.RoleVo;
import com.fziyo.sms.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    
    @PostMapping
    public Result<Void> add(@RequestBody  RoleCreateDto roleCreateDto) {
        roleService.save(roleCreateDto);
        log.info("Role created successfully {}", roleCreateDto);
        return Result.success();
    }
    
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        roleService.deleteById(id);
        log.info("Role deleted successfully {}", id);
        return Result.success();
    }
    
    @GetMapping
    public Result<List<RoleVo>> list() {
        List<RoleVo> roleVos = roleService.getAll();
        log.info("Roles get successfully {}", roleVos);
        return Result.success(roleVos);
    }
}
