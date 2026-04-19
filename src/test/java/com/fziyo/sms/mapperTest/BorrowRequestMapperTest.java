package com.fziyo.sms.mapperTest;

import com.fziyo.sms.common.constant.BorrowRequestStatus;
import com.fziyo.sms.mapper.BorrowRequestMapper;
import com.fziyo.sms.model.entity.BorrowRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BorrowRequestMapperTest {
    @Autowired
    private BorrowRequestMapper borrowRequestMapper;
    
    @Test
    void testInsert() {
        BorrowRequest borrowRequest = new BorrowRequest();
        borrowRequest.setStatus(BorrowRequestStatus.PENDING);
        borrowRequest.setAssetId(8);
        borrowRequest.setBorrowerId(100001);
        borrowRequestMapper.insert(borrowRequest);
    }
    @Test
    void testApproveRequest() {
        borrowRequestMapper.approveRequest(1, BorrowRequestStatus.APPROVED, 4);
    }
    
    @Test
    void testReturnRequest() {
        borrowRequestMapper.approveReturn(1, BorrowRequestStatus.FINISHED, 4);
    }
    
    @Test
    void testCancelRequest() {
        borrowRequestMapper.cancelRequest(1, BorrowRequestStatus.CANCELLED);
    }
    
    @Test
    void testRejectRequest() {
        borrowRequestMapper.rejectRequest(1, BorrowRequestStatus.REJECTED, 3);
    }
    
    @Test
    void testConfirmBorrow() {
        borrowRequestMapper.confirmBorrow(1,BorrowRequestStatus.IN_USE);
    }
    
    @Test
    void testRequestReturn() {
        borrowRequestMapper.requestReturn(1,BorrowRequestStatus.RETURN_PENDING);
    }
    
}
