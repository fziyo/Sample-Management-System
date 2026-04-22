package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.CategoryMapper;
import com.fziyo.sms.model.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@SpringBootTest
public class CategoryMapperTest {
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Test
    void testInsert() {
        Category category = new Category();
        category.setName("Mobile");
        category.setCode("MBL");
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insert(category);
    }
    
    @Test
    void testDeleteByIds() {
        categoryMapper.deleteById(1);
    }
    
    @Test
    void testList() {
        List<Category> assetCategories = categoryMapper.list();
        assetCategories.forEach(category -> log.info(category.toString()));
    }
}
