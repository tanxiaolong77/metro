package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.MatchMapper;
import com.metro.model.Match;
import com.metro.service.MatchService;

@Service("matchService")
public class MatchServiceImpl extends BaseService<Match, String> implements MatchService{

	@Autowired
	private MatchMapper matchMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return matchMapper;
	}

	
}
