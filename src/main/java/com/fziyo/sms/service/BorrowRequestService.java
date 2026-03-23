package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.AssetCreateDto;
import com.fziyo.sms.model.dto.AssetUpdateDto;
import com.fziyo.sms.model.dto.BorrowRequestCreateDto;
import com.fziyo.sms.model.dto.BorrowRequestUpdateDto;
import com.fziyo.sms.model.entity.BorrowRequest;
import com.fziyo.sms.model.vo.AssetVo;
import com.fziyo.sms.model.vo.BorrowRequestVo;

import java.util.List;

public interface BorrowRequestService {
    void save(BorrowRequestCreateDto borrowRequestCreateDto);
    
    void update(BorrowRequestUpdateDto borrowRequestUpdateDto);
    
    BorrowRequestVo getById(Integer id);
    
    List<BorrowRequestVo> getAll();
}
