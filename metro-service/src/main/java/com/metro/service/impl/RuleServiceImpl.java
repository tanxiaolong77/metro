package com.metro.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.RuleMapper;
import com.metro.model.Rule;
import com.metro.model.RuleExample;
import com.metro.service.RuleService;
import com.metro.util.BaseUtil;

@Service("ruleService")
public class RuleServiceImpl extends BaseService<Rule,String> implements RuleService{
  
	
	@Autowired
	private RuleMapper ruleMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return ruleMapper;
	}
	
	public void add(List<String> rules,String jobsId,String operater){
		
		for (String ruleStr : rules) {
			String[] ruleArray = ruleStr.split("#");
			Rule rule = new Rule();
			rule.setId(BaseUtil.getUUID());
			rule.setJobId(jobsId);
			rule.setContentType(ruleArray[0]);
			rule.setContentRate(ruleArray[1]);
			rule.setOneChoose(ruleArray[2]);
			rule.setManyChoose(ruleArray[3]);
			rule.setJudge(ruleArray[4]);
			rule.setCreateTime(new Date());
			rule.setOperater(operater);
			
			RuleExample example = new RuleExample();
			RuleExample.Criteria c = example.createCriteria();
			c.andContentTypeEqualTo(rule.getContentType());
			List<Rule> list = ruleMapper.selectByExample(example);
			if(list != null && list.size() > 0){
				continue;//名称相同的则不添加
			}
			ruleMapper.insertSelective(rule);
		}
	}
}