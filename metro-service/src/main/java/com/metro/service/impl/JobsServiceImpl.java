package com.metro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.JobsMapper;
import com.metro.model.Jobs;
import com.metro.model.JobsExample;
import com.metro.service.JobsService;

@Service("jobsService")
public class JobsServiceImpl extends BaseService<Jobs,String> implements JobsService{
   
	
	@Autowired
	private JobsMapper jobsMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return jobsMapper;
	}
	
	@Override
	public void jobSet(List<String> jobIds) {
		
		//全部更改为不显示
		Jobs job = new Jobs();
		job.setIsShow("1");//不显示
		JobsExample example = new JobsExample();
		updateByExample(job,example);
		
		if(jobIds != null && jobIds.size() > 0){
			//将选择显示的部分修改
			job.setIsShow("0");//显示
			JobsExample.Criteria c = example.createCriteria();
			c.andIdIn(jobIds);
			updateByExample(job,example);
		}
		
	}
	
}