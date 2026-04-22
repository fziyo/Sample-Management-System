package com.fziyo.sms.mapper;

import com.fziyo.sms.model.entity.Asset;
import com.fziyo.sms.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssetMapper {

    int insert(Asset asset);
    
    int deleteByIds(List<Integer> ids);
    
    List<Asset> list();
    
    Asset getById(Integer id);
    
    Integer countByCategoryId(Integer id);
    
    Integer countByCategoryIds(List<Integer> ids);
    
    int update(Asset asset);

}
