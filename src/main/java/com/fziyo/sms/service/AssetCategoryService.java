package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.AssetCategoryCreateDto;
import com.fziyo.sms.model.vo.AssetCategoryVo;

import java.util.List;

public interface AssetCategoryService {
    void save(AssetCategoryCreateDto assetCategoryDto);
    
    void delete(List<Integer> ids);
    
    List<AssetCategoryVo> getAll();
}
