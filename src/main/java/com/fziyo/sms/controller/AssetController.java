package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.AssetCreateDto;
import com.fziyo.sms.model.dto.AssetUpdateDto;
import com.fziyo.sms.model.entity.Asset;
import com.fziyo.sms.model.vo.AssetVo;
import com.fziyo.sms.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/asset")
@RestController
public class AssetController {
    @Autowired
    private AssetService assetService;
    
    @PostMapping
    public Result<Void> add(@RequestBody AssetCreateDto assetCreateDto) {
        assetService.save(assetCreateDto);
        log.info("Add asset: {}", assetCreateDto);
        return Result.success();
    }
    
    @DeleteMapping
    public Result<Void> deleteBatch(@RequestParam List<Integer> ids) {
        assetService.deleteByIds(ids);
        log.info("Delete asset: {}", ids);
        return Result.success();
    }
    
    @PutMapping
    public Result<Void> update(@RequestBody AssetUpdateDto assetUpdateDto) {
        assetService.update(assetUpdateDto);
        log.info("Update asset: {}", assetUpdateDto);
        return Result.success();
    }
    
    @GetMapping("/{id}")
    public Result<AssetVo> getInfo(@PathVariable Integer id) {
        AssetVo assetVo = assetService.getById(id);
        log.info("Get asset: {}", assetVo);
        return Result.success(assetVo);
    }
    
    @GetMapping
    public Result<List<AssetVo>> list() {
        List<AssetVo> assetVos = assetService.getAll();
        log.info("Get all assets: {}", assetVos);
        return Result.success(assetVos);
    }
}
