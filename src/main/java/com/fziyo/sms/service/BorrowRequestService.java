package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.AssetCreateDto;
import com.fziyo.sms.model.dto.AssetUpdateDto;
import com.fziyo.sms.model.vo.AssetVo;

import java.util.List;

public interface BorrowRequestService {
    void save(AssetCreateDto assetCreateDto);
    
    void deleteByIds(List<Integer> ids);
    
    void update(AssetUpdateDto assetUpdateDto);
    
    AssetVo getById(int id);
    
    List<AssetVo> getAll();
}
