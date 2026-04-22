package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.CategoryMapper;
import com.fziyo.sms.model.dto.AssetCategoryCreateDto;
import com.fziyo.sms.model.entity.Category;
import com.fziyo.sms.model.vo.CategoryVo;
import com.fziyo.sms.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.Mockito.verify;
@Slf4j
@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @MockitoBean
    private CategoryMapper categoryMapper;
    
    @Test
    public void testSave() {
        AssetCategoryCreateDto assetCategoryCreateDto = new AssetCategoryCreateDto();
        assetCategoryCreateDto.setName("Wearable");
        categoryService.save(assetCategoryCreateDto);
        ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
        verify(categoryMapper).insert(captor.capture());
    }
    
    @Test
    public void testDelete() {
        categoryService.deleteById(1);
    }
    
    @Test
    public void testGetAll() {
        List<CategoryVo> assetCategoriesVos = categoryService.getAll();
        assetCategoriesVos.forEach(categoryVo -> log.info(categoryVo.toString()));
    }
}
