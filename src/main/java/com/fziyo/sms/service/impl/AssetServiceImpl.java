package com.fziyo.sms.service.impl;

import com.fziyo.sms.common.exception.BusinessException;
import com.fziyo.sms.mapper.AssetMapper;
import com.fziyo.sms.model.dto.AssetCreateDto;
import com.fziyo.sms.model.dto.AssetUpdateDto;
import com.fziyo.sms.model.entity.Asset;
import com.fziyo.sms.model.vo.AssetVo;
import com.fziyo.sms.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetMapper assetMapper;
    
    @Override
    public void save(AssetCreateDto assetCreateDto) {
        Asset asset = new Asset();
        BeanUtils.copyProperties(assetCreateDto, asset);
        if (assetMapper.insert(asset) == 0) {
            throw new BusinessException("Failed to save asset");
        }
        log.info("Saved asset: {}", asset);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        if (assetMapper.countByCategoryIds(ids) != ids.size()) {
            throw new BusinessException("Asset does not exist, failed to delete");
        }
        assetMapper.deleteByIds(ids);
        log.info("Deleted asset: {}", ids);
    }
    
    @Override
    public void update(AssetUpdateDto assetUpdateDto) {
        Asset asset = new Asset();
        BeanUtils.copyProperties(assetUpdateDto, asset);
        if (assetMapper.update(asset) == 0) {
            throw new BusinessException("Failed to update asset");
        }
        log.info("Updated asset: {}", asset);
    }
    
    @Override
    public AssetVo getById(Integer id) {
        Asset asset = assetMapper.getById(id);
        if (asset == null) {
            throw new BusinessException("Asset not found");
        }
        AssetVo assetVo = new AssetVo();
        BeanUtils.copyProperties(asset, assetVo);
        log.info("Get asset: {}", assetVo);
        return assetVo;
    }
    
    @Override
    public List<AssetVo> getAll() {
        List<Asset> assets = assetMapper.list();
        if (assets == null || assets.isEmpty()) {
            throw new BusinessException("Assets not found");
        }
        log.info("Get all assets size: {}", assets.size());
        return assets.stream().map(asset -> {
            AssetVo assetVo = new AssetVo();
            BeanUtils.copyProperties(asset, assetVo);
            return assetVo;
        }).toList();
    }
}
