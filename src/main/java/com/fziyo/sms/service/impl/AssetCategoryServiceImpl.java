package com.fziyo.sms.service.impl;

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
        assetCategoryMapper.insert(assetCategory);
    }
    
    @Override
    public void delete(List<Integer> ids) {
        assetCategoryMapper.deleteByIds(ids);
    }
    
    @Override
    public List<AssetCategoryVo> getAll() {
        List<AssetCategory>  assetCategories= assetCategoryMapper.list();
        return assetCategories.stream().map(assetCategory -> {
            AssetCategoryVo assetCategoryVo = new AssetCategoryVo();
            BeanUtils.copyProperties(assetCategory, assetCategoryVo);
            return assetCategoryVo;
        }).toList();
    }
}
