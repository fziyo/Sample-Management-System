package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.BorrowRequestCreateDto;
import com.fziyo.sms.model.vo.BorrowRequestVo;

import java.util.List;

public interface BorrowRequestService {
    void save(BorrowRequestCreateDto borrowRequestCreateDto);
    
    BorrowRequestVo getById(Integer id);
    
    List<BorrowRequestVo> getAll();
    
    void approveRequest(Integer id);
}
