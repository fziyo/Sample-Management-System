package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.CategoryCreateDto;
import com.fziyo.sms.model.vo.CategoryVo;
import com.fziyo.sms.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @PreAuthorize(value = "hasAuthority('category:add)")
    @PostMapping
    public Result<Void> add(@RequestBody CategoryCreateDto categoryCreateDto) {
        categoryService.save(categoryCreateDto);
        log.info("Asset category created successfully {}", categoryCreateDto);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('category:delete)")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        categoryService.deleteById(id);
        log.info("Asset category deleted successfully {}", id);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('category:view)")
    @GetMapping
    public Result<List<CategoryVo>> list() {
        List<CategoryVo> categoryVos = categoryService.getAll();
        log.info("Asset category get successfully {}", categoryVos);
        return Result.success(categoryVos);
    }
}
