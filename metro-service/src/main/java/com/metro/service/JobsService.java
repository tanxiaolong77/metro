package com.metro.service;

import java.util.List;

import com.metro.basic.EntityService;
import com.metro.model.Jobs;

public interface JobsService extends EntityService<Jobs,String>{
	
	public void jobSet(List<String> jobIds);
   
}