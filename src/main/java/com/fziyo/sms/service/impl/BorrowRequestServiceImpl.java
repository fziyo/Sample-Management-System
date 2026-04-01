package com.fziyo.sms.service.impl;
import com.fziyo.sms.common.constant.BorrowRequestStatus;
import com.fziyo.sms.common.constant.ResponseCode;
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
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to save BorrowRequest");
        }
        log.info("Save BorrowRequest: {}", borrowRequest);
    }
    
    @Override
    public BorrowRequestVo getById(Integer id) {
        BorrowRequest borrowRequest = borrowRequestMapper.getById(id);
        if (borrowRequest == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND, "BorrowRequest not found");
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
            throw new BusinessException(ResponseCode.NOT_FOUND, "No BorrowRequest found");
        }
        log.info("Get all BorrowRequests size: {}", borrowRequests.size());
        return borrowRequests.stream().map(borrowRequest -> {
            BorrowRequestVo borrowRequestVo = new BorrowRequestVo();
            BeanUtils.copyProperties(borrowRequest,borrowRequestVo);
            return borrowRequestVo;
        }).toList();
    }
    
    @Override
    public void cancelRequest(Integer id) {
        BorrowRequest borrowRequest = borrowRequestMapper.getById(id);
        // todo: replace approverId with context userId
        // only borrower can cancel the request
        if (borrowRequest == null || borrowRequest.getBorrowerId() != 1) {
            throw new BusinessException(ResponseCode.NOT_FOUND, "BorrowRequest not found");
        }
        if (borrowRequestMapper.cancelRequest(id, BorrowRequestStatus.CANCELLED) == 0) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to cancel BorrowRequest");
        }
        log.info("Cancel BorrowRequest: {}", borrowRequest);
    }
    
    @Override
    public void approveRequest(Integer id) {
        // todo: replace approverId with context userId
        if (borrowRequestMapper.approveRequest(id, BorrowRequestStatus.APPROVED, 1) == 0) {
            throw new BusinessException(ResponseCode.NOT_FOUND, "BorrowRequest not found");
        }
        log.info("Approve BorrowRequest: {}", id);
    }
    
    @Override
    public void rejectRequest(Integer id) {
        // todo: replace approverId with context userId
        if (borrowRequestMapper.rejectRequest(id, BorrowRequestStatus.REJECTED, 1) == 0) {
            throw new BusinessException(ResponseCode.NOT_FOUND, "BorrowRequest not found");
        }
        log.info("Reject BorrowRequest: {}", id);
    }
    
    @Override
    public void confirmBorrow(Integer id) {
        // todo: replace approverId with context userId
        if (borrowRequestMapper.confirmBorrow(id, BorrowRequestStatus.IN_USE) == 0) {
            throw new BusinessException(ResponseCode.NOT_FOUND, "BorrowRequest not found");
        }
        log.info("Confirm BorrowRequest: {}", id);
    }
    
    @Override
    public void requestReturn(Integer id) {
        BorrowRequest borrowRequest = borrowRequestMapper.getById(id);
        // todo: replace approverId with context userId
        if ( borrowRequest == null || borrowRequest.getBorrowerId() != 1) {
            throw new BusinessException(ResponseCode.NOT_FOUND, "BorrowRequest not found");
        }
        if (borrowRequestMapper.requestReturn(id, BorrowRequestStatus.RETURN_PENDING) == 0) {
            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "Failed to return BorrowRequest");
        }
        log.info("Return BorrowRequest: {}", id);
    }
    
    @Override
    public void approveReturn(Integer id) {
        // todo: replace approverId with context userId
        if (borrowRequestMapper.approveReturn(id, BorrowRequestStatus.FINISHED, 1) == 0) {
            throw new BusinessException(ResponseCode.NOT_FOUND, "BorrowRequest not found");
        }
        log.info("Approve BorrowRequest return: {}", id);
    }
}
