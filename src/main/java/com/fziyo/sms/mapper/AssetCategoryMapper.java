package com.fziyo.sms.mapper;

import com.fziyo.sms.model.entity.AssetCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssetCategoryMapper {
    
    int insert(AssetCategory assetCategory);
    
    int deleteById(Integer id);
    
    List<AssetCategory> list();
    
}
