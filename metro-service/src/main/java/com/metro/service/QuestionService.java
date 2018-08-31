package com.metro.service;

import java.util.List;

import com.metro.basic.EntityService;
import com.metro.model.Question;
import com.metro.vo.QuestionVo;

public interface QuestionService extends EntityService<Question,String>{

	/**
	 * 根据岗位查看，查看题库数量
	 * @param question
	 * @return
	 */
	int countByQuestionVo(QuestionVo question);
	
	/**
	 * 根据技能类型、考试类型、岗位类型、大赛级别、题型数量出题
	 * @param question
	 */
	List<Question> selectByQuestionVo(QuestionVo question);

}