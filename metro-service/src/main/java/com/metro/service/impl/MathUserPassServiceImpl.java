package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.MatchUserPassMapper;
import com.metro.model.MatchUserPass;
import com.metro.service.MatchUserPassService;

@Service("mathUserPassService")
public class MathUserPassServiceImpl  extends BaseService<MatchUserPass,String> implements MatchUserPassService{
   
	
	@Autowired
	private MatchUserPassMapper mathUserPassMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return mathUserPassMapper;
	}
}