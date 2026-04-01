package com.fziyo.sms.service;

import com.fziyo.sms.model.dto.BorrowRequestCreateDto;
import com.fziyo.sms.model.vo.BorrowRequestVo;

import java.util.List;

public interface BorrowRequestService {
    void save(BorrowRequestCreateDto borrowRequestCreateDto);
    
    BorrowRequestVo getById(Integer id);
    
    List<BorrowRequestVo> getAll();
    
    void cancelRequest(Integer id);
    void approveRequest(Integer id);
    void rejectRequest(Integer id);
    void confirmBorrow(Integer id);
    void requestReturn(Integer id);
    void approveReturn(Integer id);
}
