package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.QuestionMapper;
import com.metro.model.Question;

@Service("questionService")
public class QuestionServiceImpl extends BaseService<Question,String>{

	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return questionMapper;
	}
}