package com.fziyo.sms.service.impl;

import com.fziyo.sms.common.exception.BusinessException;
import com.fziyo.sms.mapper.AssetCategoryMapper;
import com.fziyo.sms.model.dto.AssetCategoryCreateDto;
import com.fziyo.sms.model.entity.AssetCategory;
import com.fziyo.sms.model.vo.AssetCategoryVo;
import com.fziyo.sms.service.AssetCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {
    @Autowired
    private AssetCategoryMapper assetCategoryMapper;
    
    @Override
    public void save(AssetCategoryCreateDto assetCategoryDto) {
        AssetCategory assetCategory = new AssetCategory();
        BeanUtils.copyProperties(assetCategoryDto, assetCategory);
        if (assetCategoryMapper.insert(assetCategory) == 0) {
            throw new BusinessException("Failed to save AssetCategory");
        }
    }
    
    @Override
    public void delete(List<Integer> ids) {
        
        if (assetCategoryMapper.deleteByIds(ids) == 0) {
            throw new BusinessException("Failed to delete AssetCategory");
        }
    }
    
    @Override
    public List<AssetCategoryVo> getAll() {
        List<AssetCategory>  assetCategories= assetCategoryMapper.list();
        if (assetCategories==null || assetCategories.isEmpty()){
            throw new BusinessException("No AssetCategory found");
        }
        return assetCategories.stream().map(assetCategory -> {
            AssetCategoryVo assetCategoryVo = new AssetCategoryVo();
            BeanUtils.copyProperties(assetCategory, assetCategoryVo);
            return assetCategoryVo;
        }).toList();
    }
}
