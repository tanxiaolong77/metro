package com.metro.dao;

import java.util.List;

import com.metro.basic.EntityDao;
import com.metro.model.Question;
import com.metro.vo.QuestionVo;

public interface QuestionMapper extends EntityDao<Question,String>{

	List<QuestionVo> selectByQuestionVo(QuestionVo questionVo);

}