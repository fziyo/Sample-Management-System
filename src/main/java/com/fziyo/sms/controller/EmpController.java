package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.EmpCreateDto;
import com.fziyo.sms.model.dto.EmpUpdateDto;
import com.fziyo.sms.model.vo.EmpVo;
import com.fziyo.sms.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/emp")
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
    
    @GetMapping("/list")
    public Result<List<EmpVo>> list() {
        List<EmpVo> empVos = empService.getAll();
        log.info("Get all employees success, size: {}", empVos.size());
        return Result.success(empVos);
    }
    
    @DeleteMapping
    public Result<Void> deleteBatch(@RequestParam List<Integer> ids) {
        empService.deleteByIds(ids);
        log.info("Deleted employees success, size: {}", ids.size());
        return Result.success();
    }
    
    @PutMapping
    public Result<Void> update(@RequestBody EmpUpdateDto empUpdateDto) {
        empService.update(empUpdateDto);
        log.info("Updated employee success");
        return Result.success();
    }
    
    @GetMapping("/{id}")
    public Result<EmpVo> getInfo(@PathVariable Integer id) {
        EmpVo empVo = empService.getById(id);
        log.info("Get employee success, id: {}", id);
        return Result.success(empVo);
    }
}
