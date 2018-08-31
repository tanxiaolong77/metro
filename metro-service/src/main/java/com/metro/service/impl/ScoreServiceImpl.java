package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.ScoreMapper;
import com.metro.model.Score;
import com.metro.service.ScoreService;

@Service("scoreService")
public class ScoreServiceImpl  extends BaseService<Score,String> implements ScoreService{
   
	
	@Autowired
	private ScoreMapper scoreMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return scoreMapper;
	}
}