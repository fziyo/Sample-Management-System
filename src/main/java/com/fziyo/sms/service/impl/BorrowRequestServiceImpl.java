package com.fziyo.sms.service.impl;
import com.fziyo.sms.common.constant.BorrowRequestStatus;
import com.fziyo.sms.common.exception.BusinessException;
import com.fziyo.sms.mapper.BorrowRequestMapper;
import com.fziyo.sms.model.dto.BorrowRequestCreateDto;
import com.fziyo.sms.model.entity.BorrowRequest;
import com.fziyo.sms.model.vo.BorrowRequestVo;
import com.fziyo.sms.service.BorrowRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BorrowRequestServiceImpl implements BorrowRequestService {
    @Autowired
    private BorrowRequestMapper borrowRequestMapper;
    // all
    @Override
    public void save(BorrowRequestCreateDto borrowRequestCreateDto) {
        BorrowRequest borrowRequest = new BorrowRequest();
        BeanUtils.copyProperties(borrowRequestCreateDto,borrowRequest);
        borrowRequest.setStatus(BorrowRequestStatus.PENDING);
        borrowRequest.setBorrowerId(1); //todo: replace borrowerId with context userId
        if (borrowRequestMapper.insert(borrowRequest) == 0) {
            throw new BusinessException("Failed to save BorrowRequest");
        }
        log.info("Save BorrowRequest: {}", borrowRequest);
    }
    
    @Override
    public BorrowRequestVo getById(Integer id) {
        BorrowRequest borrowRequest = borrowRequestMapper.getById(id);
        if (borrowRequest == null) {
            throw new BusinessException("BorrowRequest not found");
        }
        BorrowRequestVo borrowRequestVo = new BorrowRequestVo();
        BeanUtils.copyProperties(borrowRequest,borrowRequestVo);
        log.info("Get BorrowRequest: {}", borrowRequestVo);
        return borrowRequestVo;
    }
    
    @Override
    public List<BorrowRequestVo> getAll() {
        List<BorrowRequest> borrowRequests = borrowRequestMapper.list();
        if (borrowRequests == null || borrowRequests.isEmpty()) {
            throw new BusinessException("No BorrowRequest found");
        }
        log.info("Get all BorrowRequests size: {}", borrowRequests.size());
        return borrowRequests.stream().map(borrowRequest -> {
            BorrowRequestVo borrowRequestVo = new BorrowRequestVo();
            BeanUtils.copyProperties(borrowRequest,borrowRequestVo);
            return borrowRequestVo;
        }).toList();
    }
    
    @Override
    public void approveRequest(Integer id) {
        // todo: replace approverId with context userId
        if (borrowRequestMapper.approveRequest(id, BorrowRequestStatus.APPROVED, 1) == 0) {
            throw new BusinessException("BorrowRequest not found");
        }
        log.info("Approve BorrowRequest: {}", borrowRequestMapper.getById(id));
    }
}
