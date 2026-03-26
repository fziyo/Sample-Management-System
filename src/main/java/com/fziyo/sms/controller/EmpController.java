package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.EmpCreateDto;
import com.fziyo.sms.model.vo.EmpVo;
import com.fziyo.sms.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emp")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    
    @PostMapping
    public Result<Void> add(@RequestBody  EmpCreateDto empCreateDto) {
        empService.save(empCreateDto);
        log.info("Saved emp: {}", empCreateDto);
        return Result.success();
    }
    
    @GetMapping
    public Result<List<EmpVo>> list() {
        List<EmpVo> empVos = empService.getAll();
        log.info("Get all employees success, size: {}", empVos.size());
        return Result.success(empVos);
    }
}
