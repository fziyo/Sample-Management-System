package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.AssetCategoryMapper;
import com.fziyo.sms.model.entity.AssetCategory;
import com.fziyo.sms.model.entity.Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@SpringBootTest
public class AssetCategoryMapperTest {
    @Autowired
    private AssetCategoryMapper assetCategoryMapper;
    
    @Test
    void testInsert() {
        AssetCategory assetCategory = new AssetCategory();
        assetCategory.setName("Mobile");
        assetCategory.setCreateTime(LocalDateTime.now());
        assetCategory.setUpdateTime(LocalDateTime.now());
        assetCategoryMapper.insert(assetCategory);
    }
    
    @Test
    void testDeleteByIds() {
        List<Integer> ids = List.of(1,2,3);
        assetCategoryMapper.deleteByIds(ids);
    }
    
    @Test
    void testList() {
        List<AssetCategory> assetCategories = assetCategoryMapper.list();
        assetCategories.forEach(assetCategory -> log.info(assetCategory.toString()));
    }
}
