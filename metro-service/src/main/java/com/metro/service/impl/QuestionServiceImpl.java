package com.metro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.QuestionMapper;
import com.metro.model.Question;
import com.metro.service.QuestionService;
import com.metro.vo.QuestionVo;

@Service("questionService")
public class QuestionServiceImpl extends BaseService<Question,String> implements QuestionService {

	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return questionMapper;
	}

	@Override
	public List<Question> selectByQuestionVo(QuestionVo questionVo) {
		return questionMapper.selectByQuestionVo(questionVo);
	}
}