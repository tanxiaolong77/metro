package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.AnswerMapper;
import com.metro.model.Answer;
import com.metro.service.AnswerService;

@Service("answerService")
public class AnswerServiceImpl extends BaseService<Answer, String> implements AnswerService{

	@Autowired
	private AnswerMapper answerMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return answerMapper;
	}

	
}
