package com.fziyo.sms.mapper;

import com.fziyo.sms.model.dto.BorrowRequestCreateDto;
import com.fziyo.sms.model.entity.Asset;
import com.fziyo.sms.model.entity.BorrowRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface  BorrowRequestMapper {
    // 0=PENDING
    int insert(BorrowRequest borrowRequest);
    
    @Select("Select * from borrow_request where id = #{id}")
    BorrowRequest getById(int id);
    
    int deleteByIds(List<Integer> ids);
    
    @Select("Select * from borrow_request")
    List<BorrowRequest> list();
    
    // 1=CANCELLED
    int cancelRequest(Integer id, int cancelledStatus);
    // 2=APPROVED
    int approveRequest(Integer id, int approveStatus, Integer approverId);
    // 3=REJECTED
    int rejectRequest(Integer id, int rejectedStatus, Integer approverId);
    // 4=IN_USE
    int confirmBorrow(Integer id, int inUseStatus);
    // 5=RETURN_PENDING
    int requestReturn(Integer id, int returnPendingStatus);
    // 6=FINISHED
    int approveReturn(Integer id, int finishedStatus, Integer approverId);
    
}
