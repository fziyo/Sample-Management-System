package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.AssetCategoryCreateDto;
import com.fziyo.sms.model.dto.RoleCreateDto;
import com.fziyo.sms.model.vo.AssetCategoryVo;
import com.fziyo.sms.model.vo.RoleVo;
import com.fziyo.sms.service.AssetCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/asset-category")
@RestController
public class AssetCategoryController {
    @Autowired
    private AssetCategoryService assetCategoryService;
    
    @PostMapping
    public Result<Void> add(@RequestBody AssetCategoryCreateDto assetCategoryCreateDto) {
        assetCategoryService.save(assetCategoryCreateDto);
        log.info("Asset category created successfully {}", assetCategoryCreateDto);
        return Result.success();
    }
    
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        assetCategoryService.deleteById(id);
        log.info("Asset category deleted successfully {}", id);
        return Result.success();
    }
    
    @GetMapping
    public Result<List<AssetCategoryVo>> list() {
        List<AssetCategoryVo> assetCategoryVos = assetCategoryService.getAll();
        log.info("Asset category get successfully {}", assetCategoryVos);
        return Result.success(assetCategoryVos);
    }
}
