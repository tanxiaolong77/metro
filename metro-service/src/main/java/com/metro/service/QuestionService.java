package com.metro.service;

import java.util.ArrayList;
import java.util.List;

import com.metro.basic.EntityService;
import com.metro.model.BaseExample;
import com.metro.model.Question;
import com.metro.vo.QuestionVo;

public interface QuestionService extends EntityService<Question,String>{

	/**
	 * 根据技能类型、考试类型、岗位类型、大赛级别、题型数量出题
	 * @param question
	 */
	List<QuestionVo> selectByQuestionVo(QuestionVo question);

	List<QuestionVo> selectByQuestionId(String questionConditions);

	List<Question> selectByIds(BaseExample example);
}