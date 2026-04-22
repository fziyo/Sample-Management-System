package com.fziyo.sms.mapper;

import com.fziyo.sms.model.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    
    int insert(Category category);
    
    int deleteById(Integer id);
    
    List<Category> list();
    
}
