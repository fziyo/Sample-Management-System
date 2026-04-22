package com.fziyo.sms.controller;

import com.fziyo.sms.common.Result;
import com.fziyo.sms.model.dto.BorrowRequestCreateDto;
import com.fziyo.sms.model.vo.BorrowRequestVo;
import com.fziyo.sms.service.BorrowRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/borrow")
@RestController
public class BorrowRequestController {
    @Autowired
    private BorrowRequestService borrowRequestService;
    
    @PreAuthorize(value = "hasAuthority('borrow-request:create')")
    @PostMapping
    public Result<Void> save(@RequestBody BorrowRequestCreateDto borrowRequestCreateDto) {
        borrowRequestService.save(borrowRequestCreateDto);
        log.info("Created BorrowRequest success");
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('borrow-request:view')")
    @GetMapping
    public Result<List<BorrowRequestVo>> list() {
        List<BorrowRequestVo> brVos = borrowRequestService.getAll();
        log.info("Get BorrowRequests successfully {}", brVos.size());
        return Result.success(brVos);
    }
    
    @PreAuthorize(value = "hasAuthority('borrow-request:approve')")
    @PostMapping("/{id}/approve")
    public Result<Void> approveRequest(@PathVariable Integer id) {
        borrowRequestService.approveRequest(id);
        log.info("Approved BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('borrow-request:cancel')")
    @PostMapping("/{id}/cancel")
    public Result<Void> cancelRequest(@PathVariable Integer id) {
        borrowRequestService.cancelRequest(id);
        log.info("Cancelled BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('borrow-request:reject')")
    @PostMapping("/{id}/reject")
    public Result<Void> rejectRequest(@PathVariable Integer id) {
        borrowRequestService.rejectRequest(id);
        log.info("Rejected BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('borrow-request:confirm')")
    @PostMapping("/{id}/confirm")
    public Result<Void> confirmBorrow(@PathVariable Integer id) {
        borrowRequestService.confirmBorrow(id);
        log.info("Confirmed BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('borrow-request:return')")
    @PostMapping("/{id}/return/request")
    public Result<Void> requestReturn(@PathVariable Integer id) {
        borrowRequestService.requestReturn(id);
        log.info("Requested BorrowRequest successfully {}", id);
        return Result.success();
    }
    
    @PreAuthorize(value = "hasAuthority('borrow-request:approve-return')")
    @PostMapping("/{id}/return/approve")
    public Result<Void> approveReturn(@PathVariable  Integer id) {
        borrowRequestService.approveReturn(id);
        log.info("Approved BorrowRequest return successfully {}", id);
        return Result.success();
    }
}
