package com.fziyo.sms.mapper;

import com.fziyo.sms.model.entity.AssetCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssetCategoryMapper {
    @Insert("Insert into asset_category(name) " +
                "values (#{name})")
    int insert(AssetCategory assetCategory);
    
    int deleteByIds(List<Integer> ids);
    
    @Select("Select * from asset_category")
    List<AssetCategory> list();
    
}
