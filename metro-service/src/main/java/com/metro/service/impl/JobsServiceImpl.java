package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.JobsMapper;
import com.metro.model.Jobs;

@Service("jobsService")
public class JobsServiceImpl extends BaseService<Jobs,String>{
   
	
	@Autowired
	private JobsMapper jobsMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return jobsMapper;
	}
}