package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.CategoryCreateDto;
import com.fziyo.sms.model.vo.CategoryVo;

import java.util.List;

public interface CategoryService {
    void save(CategoryCreateDto assetCategoryDto);
    
    void deleteById(Integer id);
    
    List<CategoryVo> getAll();
}
