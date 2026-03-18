package com.fziyo.sms.serviceTest;

import com.fziyo.sms.mapper.AssetMapper;
import com.fziyo.sms.model.dto.AssetCreateDto;
import com.fziyo.sms.model.dto.AssetUpdateDto;
import com.fziyo.sms.model.entity.Asset;
import com.fziyo.sms.service.AssetService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class AssetServiceTest {
    @Autowired
    private AssetService assetService;
    @MockitoBean
    private AssetMapper assetMapper;
    @Test
    public void testInsert() {
        AssetCreateDto assetCreateDto = new AssetCreateDto();
        assetService.save(assetCreateDto);
        ArgumentCaptor<Asset> captor = ArgumentCaptor.forClass(Asset.class);
        verify(assetMapper).insert(captor.capture());
    }
    
    @Test
    public void testUpdate() {
        AssetUpdateDto  assetUpdateDto = new AssetUpdateDto();
        assetUpdateDto.setId(2);
        assetService.update(assetUpdateDto);
        ArgumentCaptor<Asset> captor = ArgumentCaptor.forClass(Asset.class);
        verify(assetMapper).update(captor.capture());
    }
    
    @Test
    public void testDeleteByIds() {
        List<Integer> ids = List.of(1,2,3);
        assetService.deleteByIds(ids);
        verify(assetMapper).deleteByIds(ids);
    }
    
    @Test
    public void testGetById() {
        assetService.getById(2);
        verify(assetMapper).getById(2);
    }
    
    @Test
    public void testGetAll() {
        assetService.getAll();
        verify(assetMapper).list();
    
    }
}
