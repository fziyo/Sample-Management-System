package com.fziyo.sms.mapperTest;

import com.fziyo.sms.mapper.AssetMapper;
import com.fziyo.sms.model.entity.Asset;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
@Slf4j
@SpringBootTest
public class AssetMapperTest {
    @Autowired
    AssetMapper assetMapper;
    
    @Test
    public void testInsert(){
        Asset asset = new Asset();
        asset.setAssetCode("AS01");
        asset.setName("iPhone 15");
        asset.setModel("A3090");
        asset.setCategoryId(1);
        asset.setTeamId(1);
        asset.setOwnerId(1);
        asset.setSn("SN123456789");
        asset.setMacAddr("AA:BB:CC:DD:EE:FF");
        asset.setReleaseYear(Year.of(2023));
        asset.setAssetCondition(1);
        asset.setCommercialStatus(1);
        asset.setIsActive(1);
        asset.setStatus(1);
        asset.setCurrentUserId(1);
        asset.setLocation("Warsaw Office");
        asset.setDescription("Test device");
        
        int rows = assetMapper.insert(asset);
        
        assert(rows == 1);
        log.info("id: " + asset.getId());
    }
    
    @Test
    public void testList() {
        List<Asset> assets = assetMapper.list();
        log.info(assets.toString());
    }
    
    @Test
    public void testDeleteByIds(){
        List<Integer> ids = List.of(1,2,3);
        assetMapper.deleteByIds(ids);
        
    }
    
    @Test
    public void testUpdate(){
        Asset asset = new Asset();
        asset.setId(2);
        asset.setAssetCode("AS02");
        asset.setName("iPhone 15");
        asset.setModel("A2090");
        asset.setCategoryId(2);
        asset.setTeamId(1);
        asset.setOwnerId(1);
        asset.setSn("SN120000000");
        asset.setMacAddr("AA:BB:CC:DD:EE:FF");
        asset.setReleaseYear(Year.of(2023));
        asset.setAssetCondition(1);
        asset.setCommercialStatus(1);
        asset.setIsActive(1);
        asset.setStatus(1);
        asset.setCurrentUserId(1);
        asset.setLocation("Warsaw Office");
        asset.setDescription("Test device");
        assetMapper.update(asset);
        log.info("updated: " + asset.getId());
    }
    
    @Test
    public void testGetById() {
        log.info(assetMapper.getById(2).toString());
    }
    
}
