package com.metro.service;

import java.util.List;

import com.metro.basic.EntityService;
import com.metro.model.Rule;

public interface RuleService extends EntityService<Rule,String>{
  
	public void add(List<String> rules,String jobsId,String operater);
}