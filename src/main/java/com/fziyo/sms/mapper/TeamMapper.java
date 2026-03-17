package com.fziyo.sms.mapper;

import com.fziyo.sms.model.dto.TeamCreateDto;
import com.fziyo.sms.model.entity.Team;
import com.fziyo.sms.model.vo.TeamVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeamMapper {
    @Insert("Insert into team(name, create_time, update_time) " +
                "values (#{name}, #{createTime}, #{updateTime})")
    void insert(Team team);
    
    void deleteByIds(List<Integer> ids);
    
    @Select("Select * from team")
    List<TeamVo> list();
}
