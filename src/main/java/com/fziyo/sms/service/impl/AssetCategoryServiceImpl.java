package com.fziyo.sms.service.impl;

import com.fziyo.sms.common.constant.ResponseCode;
import com.fziyo.sms.common.exception.BusinessException;
import com.fziyo.sms.mapper.AssetCategoryMapper;
import com.fziyo.sms.mapper.AssetMapper;
import com.fziyo.sms.model.dto.AssetCategoryCreateDto;
import com.fziyo.sms.model.entity.Asset;
import com.fziyo.sms.model.entity.AssetCategory;
import com.fziyo.sms.model.vo.AssetCategoryVo;
import com.fziyo.sms.service.AssetCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {
    @Autowired
    private AssetCategoryMapper assetCategoryMapper;
    @Autowired
    private AssetMapper assetMapper;
    
    @Override
    public void save(AssetCategoryCreateDto assetCategoryDto) {
        AssetCategory assetCategory = new AssetCategory();
        BeanUtils.copyProperties(assetCategoryDto, assetCategory);
        if (assetCategoryMapper.insert(assetCategory) == 0) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to save AssetCategory");
        }
        log.info("Saved assetCategory, assetCategory: {}", assetCategory);
    }
    
    @Override
    public void deleteById(Integer id) {
        if (assetMapper.countByCategoryId(id) > 0) {
            throw new BusinessException(ResponseCode.CONFLICT, "AssetCategory is used by assets, failed to delete");
        }
        if (assetCategoryMapper.deleteById(id) == 0) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to delete AssetCategory");
        }
        log.info("Deleted assetCategory, id: {}", id);
    }
    
    @Override
    public List<AssetCategoryVo> getAll() {
        List<AssetCategory>  assetCategories= assetCategoryMapper.list();
        if (assetCategories==null || assetCategories.isEmpty()){
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        log.info("Get all AssetCategory, assetCategories size: {}", assetCategories.size());
        return assetCategories.stream().map(assetCategory -> {
            AssetCategoryVo assetCategoryVo = new AssetCategoryVo();
            BeanUtils.copyProperties(assetCategory, assetCategoryVo);
            return assetCategoryVo;
        }).toList();
    }
}
