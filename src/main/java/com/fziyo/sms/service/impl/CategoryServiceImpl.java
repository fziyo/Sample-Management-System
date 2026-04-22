package com.fziyo.sms.service.impl;

import com.fziyo.sms.common.constant.ResponseCode;
import com.fziyo.sms.common.exception.BusinessException;
import com.fziyo.sms.mapper.CategoryMapper;
import com.fziyo.sms.mapper.AssetMapper;
import com.fziyo.sms.model.dto.AssetCategoryCreateDto;
import com.fziyo.sms.model.entity.Category;
import com.fziyo.sms.model.vo.CategoryVo;
import com.fziyo.sms.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private AssetMapper assetMapper;
    
    @Override
    public void save(AssetCategoryCreateDto assetCategoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(assetCategoryDto, category);
        if (categoryMapper.insert(category) == 0) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to save AssetCategory");
        }
        log.info("Saved assetCategory, assetCategory: {}", category);
    }
    
    @Override
    public void deleteById(Integer id) {
        if (assetMapper.countByCategoryId(id) > 0) {
            throw new BusinessException(ResponseCode.CONFLICT, "AssetCategory is used by assets, failed to delete");
        }
        if (categoryMapper.deleteById(id) == 0) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to delete AssetCategory");
        }
        log.info("Deleted assetCategory, id: {}", id);
    }
    
    @Override
    public List<CategoryVo> getAll() {
        List<Category>  assetCategories= categoryMapper.list();
        if (assetCategories==null || assetCategories.isEmpty()){
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        log.info("Get all AssetCategory, assetCategories size: {}", assetCategories.size());
        return assetCategories.stream().map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            return categoryVo;
        }).toList();
    }
}
