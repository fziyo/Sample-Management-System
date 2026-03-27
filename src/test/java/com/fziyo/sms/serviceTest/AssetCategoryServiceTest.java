package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.AssetCategoryMapper;
import com.fziyo.sms.model.dto.AssetCategoryCreateDto;
import com.fziyo.sms.model.entity.AssetCategory;
import com.fziyo.sms.model.vo.AssetCategoryVo;
import com.fziyo.sms.service.AssetCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
@Slf4j
@SpringBootTest
public class AssetCategoryServiceTest {
    @Autowired
    private AssetCategoryService assetCategoryService;
    @MockitoBean
    private AssetCategoryMapper assetCategoryMapper;
    
    @Test
    public void testSave() {
        AssetCategoryCreateDto assetCategoryCreateDto = new AssetCategoryCreateDto();
        assetCategoryCreateDto.setName("Wearable");
        assetCategoryService.save(assetCategoryCreateDto);
        ArgumentCaptor<AssetCategory> captor = ArgumentCaptor.forClass(AssetCategory.class);
        verify(assetCategoryMapper).insert(captor.capture());
    }
    
    @Test
    public void testDelete() {
        assetCategoryService.deleteById(1);
    }
    
    @Test
    public void testGetAll() {
        List<AssetCategoryVo> assetCategoriesVos = assetCategoryService.getAll();
        assetCategoriesVos.forEach(assetCategoryVo -> log.info(assetCategoryVo.toString()));
    }
}
