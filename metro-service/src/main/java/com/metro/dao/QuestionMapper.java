package com.metro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.metro.basic.EntityDao;
import com.metro.model.BaseExample;
import com.metro.model.Question;
import com.metro.vo.QuestionVo;

public interface QuestionMapper extends EntityDao<Question,String>{

	List<QuestionVo> selectByQuestionVo(QuestionVo questionVo);

	List<QuestionVo> selectByQuestionId(@Param("questionConditions") String questionConditions);

	List<Question> selectByIds(BaseExample example);
}