package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.UserCreateDto;
import com.fziyo.sms.model.dto.UserUpdateDto;
import com.fziyo.sms.model.vo.UserVo;
import com.fziyo.sms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    @PreAuthorize(value = "hasAuthority('user:add)")
    @PostMapping
    public Result<Void> add(@RequestBody UserCreateDto userCreateDto) {
        userService.save(userCreateDto);
        log.info("Saved emp: {}", userCreateDto);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('user:view)")
    @GetMapping
    public Result<List<UserVo>> list() {
        List<UserVo> userVos = userService.getAll();
        log.info("Get all employees success, size: {}", userVos.size());
        return Result.success(userVos);
    }
    
    @PreAuthorize(value = "hasAuthority('user:delete)")
    @DeleteMapping
    public Result<Void> deleteBatch(@RequestParam List<Integer> ids) {
        userService.deleteByIds(ids);
        log.info("Deleted employees success, size: {}", ids.size());
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('user:update)")
    @PutMapping
    public Result<Void> update(@RequestBody UserUpdateDto userUpdateDto) {
        userService.update(userUpdateDto);
        log.info("Updated employee success");
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('user:view)")
    @GetMapping("/{id}")
    public Result<UserVo> getInfo(@PathVariable Integer id) {
        UserVo userVo = userService.getById(id);
        log.info("Get employee success, id: {}", id);
        return Result.success(userVo);
    }
}
