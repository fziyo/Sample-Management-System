package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.AssetCategoryCreateDto;
import com.fziyo.sms.model.vo.CategoryVo;
import com.fziyo.sms.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/asset-category")
@RestController
public class AssetCategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @PostMapping
    public Result<Void> add(@RequestBody AssetCategoryCreateDto assetCategoryCreateDto) {
        categoryService.save(assetCategoryCreateDto);
        log.info("Asset category created successfully {}", assetCategoryCreateDto);
        return Result.success();
    }
    
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        categoryService.deleteById(id);
        log.info("Asset category deleted successfully {}", id);
        return Result.success();
    }
    
    @GetMapping
    public Result<List<CategoryVo>> list() {
        List<CategoryVo> categoryVos = categoryService.getAll();
        log.info("Asset category get successfully {}", categoryVos);
        return Result.success(categoryVos);
    }
}
