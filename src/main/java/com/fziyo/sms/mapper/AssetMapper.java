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
    
    @Select("Select * from asset")
    List<Asset> list();
    
    @Select("Select * from asset where id = #{id}")
    Asset getById(Integer id);
    
    int update(Asset asset);

}
