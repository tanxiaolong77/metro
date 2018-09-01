package com.metro.dao;

import com.metro.model.Match;
import com.metro.model.MatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MatchMapper {
    long countByExample(MatchExample example);

    int deleteByExample(MatchExample example);

    int insert(Match record);

    int insertSelective(Match record);

    List<Match> selectByExample(MatchExample example);

    int updateByExampleSelective(@Param("record") Match record, @Param("example") MatchExample example);

    int updateByExample(@Param("record") Match record, @Param("example") MatchExample example);
}