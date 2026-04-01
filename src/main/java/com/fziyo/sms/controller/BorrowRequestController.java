package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.BorrowRequestCreateDto;
import com.fziyo.sms.model.vo.BorrowRequestVo;
import com.fziyo.sms.service.BorrowRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/borrow-request")
@RestController
public class BorrowRequestController {
    @Autowired
    private BorrowRequestService borrowRequestService;
    
    @PostMapping
    public Result<Void> save(@RequestBody BorrowRequestCreateDto borrowRequestCreateDto) {
        borrowRequestService.save(borrowRequestCreateDto);
        log.info("Created BorrowRequest success");
        return Result.success();
    }
    
    @GetMapping
    public Result<List<BorrowRequestVo>> list() {
        List<BorrowRequestVo> brVos = borrowRequestService.getAll();
        log.info("Get BorrowRequests successfully {}", brVos.size());
        return Result.success(brVos);
    }
    
    @PostMapping("/{id}/approve-request")
    public Result<Void> approveRequest(@PathVariable Integer id) {
        borrowRequestService.approveRequest(id);
        log.info("Approved BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PostMapping("/{id}/cancel-request")
    public Result<Void> cancelRequest(@PathVariable Integer id) {
        borrowRequestService.cancelRequest(id);
        log.info("Cancelled BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PostMapping("/{id}/reject-request")
    public Result<Void> rejectRequest(@PathVariable Integer id) {
        borrowRequestService.rejectRequest(id);
        log.info("Rejected BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PostMapping("/{id}/confirm-borrow")
    public Result<Void> confirmBorrow(@PathVariable Integer id) {
        borrowRequestService.confirmBorrow(id);
        log.info("Confirmed BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PostMapping("/{id}/request-return")
    public Result<Void> requestReturn(@PathVariable Integer id) {
        borrowRequestService.requestReturn(id);
        log.info("Requested BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PostMapping("/{id}/approve-return")
    public Result<Void> approveReturn(@PathVariable  Integer id) {
        borrowRequestService.approveReturn(id);
        log.info("Approved BorrowRequest return successfully {}", id);
        return Result.success();
    }
}
