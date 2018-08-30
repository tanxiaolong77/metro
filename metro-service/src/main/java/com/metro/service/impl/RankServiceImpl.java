package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.RankMapper;
import com.metro.model.Rank;

@Service("rankService")
public class RankServiceImpl extends BaseService<Rank,String>{
    
	
	@Autowired
	private RankMapper rankMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return rankMapper;
	}
}