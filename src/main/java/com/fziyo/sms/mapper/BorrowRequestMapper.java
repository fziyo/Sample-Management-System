package com.fziyo.sms.mapper;

import com.fziyo.sms.model.dto.BorrowRequestCreateDto;
import com.fziyo.sms.model.entity.Asset;
import com.fziyo.sms.model.entity.BorrowRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface  BorrowRequestMapper {

    int insert(BorrowRequest borrowRequest);
    
    @Select("Select * from borrow_request where id = #{id}")
    BorrowRequest getById(int id);
    
    
    int deleteByIds(List<Integer> ids);
    
    @Select("Select * from borrow_request")
    List<BorrowRequest> list();
    
    
    int update(BorrowRequest borrowRequest);
    
}
