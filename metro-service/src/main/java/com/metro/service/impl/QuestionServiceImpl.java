package com.metro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.QuestionMapper;
import com.metro.model.BaseExample;
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
	public List<QuestionVo> selectByQuestionVo(QuestionVo questionVo) {
		return questionMapper.selectByQuestionVo(questionVo);
	}

	@Override
	public List<QuestionVo> selectByQuestionId(String questionConditions) {
		return questionMapper.selectByQuestionId(questionConditions);
	}
	
	@Override
	public List<Question> selectByIds(BaseExample example){
		return questionMapper.selectByIds(example);
	}
}