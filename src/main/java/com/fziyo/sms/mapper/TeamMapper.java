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
    @Insert("Insert into team(name) " +
                "values (#{name})")
    int insert(Team team);
    
    int deleteByIds(List<Integer> ids);
    
    @Select("Select * from team")
    List<Team> list();
}
