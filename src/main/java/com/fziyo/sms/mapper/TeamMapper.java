package com.fziyo.sms.mapper;

import com.fziyo.sms.model.dto.TeamCreateDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMapper {
    @Insert("Insert into team(name, create_time, update_time) " +
                "values (#{name}, #{createTime}, #{updateTime})")
    void insert(TeamCreateDto dto);
    
    void deleteByIds(List<Integer> ids);
    
}
