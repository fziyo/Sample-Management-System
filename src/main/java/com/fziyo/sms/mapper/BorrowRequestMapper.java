package com.fziyo.sms.mapper;

import com.fziyo.sms.model.dto.BorrowRequestCreateDto;
import com.fziyo.sms.model.entity.BorrowRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BorrowRequestMapper {

    int insert(BorrowRequestCreateDto borrowRequestCreateDto);
    
    @Select("Select * from borrow_request where id = #{id}")
    BorrowRequest getById(int id);
}
