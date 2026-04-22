package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.AssetCategoryCreateDto;
import com.fziyo.sms.model.vo.CategoryVo;

import java.util.List;

public interface CategoryService {
    void save(AssetCategoryCreateDto assetCategoryDto);
    
    void deleteById(Integer id);
    
    List<CategoryVo> getAll();
}
