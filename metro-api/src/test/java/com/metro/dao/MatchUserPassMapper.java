package com.metro.dao;

import com.metro.model.MatchUserPass;
import com.metro.model.MatchUserPassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MatchUserPassMapper {
    long countByExample(MatchUserPassExample example);

    int deleteByExample(MatchUserPassExample example);

    int deleteByPrimaryKey(String id);

    int insert(MatchUserPass record);

    int insertSelective(MatchUserPass record);

    List<MatchUserPass> selectByExample(MatchUserPassExample example);

    MatchUserPass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MatchUserPass record, @Param("example") MatchUserPassExample example);

    int updateByExample(@Param("record") MatchUserPass record, @Param("example") MatchUserPassExample example);

    int updateByPrimaryKeySelective(MatchUserPass record);

    int updateByPrimaryKey(MatchUserPass record);
}