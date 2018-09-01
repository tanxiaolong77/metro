package com.metro.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metro.model.Jobs;
import com.metro.model.JobsExample;
import com.metro.model.Rule;
import com.metro.model.RuleExample;
import com.metro.service.JobsService;
import com.metro.service.RuleService;
import com.metro.util.SessionUtils;
import com.metro.vo.DataTransObj;

/***
 * 岗位控制
 * @author dell
 *
 */
@Controller
@RequestMapping(value="rule")
public class RuleController  extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(RuleController.class);
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	private RuleService ruleService;
	
	
	/**
	 * 出题规则列表
	 * 
	 */
	@RequestMapping(value = "rulelist.m", method = RequestMethod.GET)
	public String rulelist(ModelMap model) {
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);//岗位
		return "rulelist.html";
	}
	
	/**
	 * 出题规则列表数据
	 * 
	 */
	@RequestMapping(value = "ruleSearch.m", method = RequestMethod.GET)
	public @ResponseBody DataTransObj ruleSearch(
			@RequestParam(value="skillType") String skillType,
			@RequestParam(value="jobsId") String jobsId,
			@RequestParam(value="startNumber") Integer startNumber,
			@RequestParam(value="pageSize") Integer pageSize) {
		
		
		RuleExample example = new RuleExample();
		RuleExample.Criteria c = example.createCriteria();
		
		
		if(StringUtils.isNotBlank(skillType)){
			c.andSkillTypeEqualTo(skillType);
		}
		
		if(StringUtils.isNotBlank(jobsId)){
			c.andJobIdEqualTo(jobsId);
		}
		
		int totalNum = ruleService.countByExample(example);
		
		//分页
		if(startNumber != null && pageSize != null){
			example.setLimitStart(startNumber);
			example.setLimitEnd(pageSize);
		}
		
		List<Rule> list = ruleService.selectByExample(example);
		
		return DataTransObj.onSuccess(list, "查询成功", totalNum);
	}
	
	/**
	 * 出题规则新建页面
	 * 
	 */
	@RequestMapping(value = "ruleSetInit.m", method = RequestMethod.GET)
	public String ruleSetInit(ModelMap model) {
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);//岗位
		return "rule-add.html";
	}
	
	/**
	 * 新建、修改出题规则
	 * 
	 */
	@RequestMapping(value = "ruleSet.m", method = RequestMethod.POST)
	public @ResponseBody DataTransObj ruleSet(
			@RequestParam(value="skillType", required = true) String skillType,
			@RequestParam(value="jobsId", required = true) String jobsId,
			@RequestParam(value="operationType", required = true) String operationType,
			@RequestParam(value="contentRate", required = true) String contentRate,
			@RequestParam(value="rules", required = true) List<String> rules
			) {
		
		if(rules != null && rules.size() > 0){
			
//			if("2".equals(operationType)){
//				//修改则把之前的规则删除
//				RuleExample example = new RuleExample();
//				RuleExample.Criteria c = example.createCriteria();
//				c.andSkillTypeEqualTo(skillType);
//				c.andJobIdEqualTo(jobsId);
//				ruleService.deleteByExample(example);
//			}
			
			try {
				//新增
				ruleService.add(rules, skillType, jobsId, SessionUtils.getLoginUser().getId(),contentRate);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				return DataTransObj.onFailure(null,"新增失败");
			}
		
			
		}

		return DataTransObj.onSuccess(null,"操作成功");
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "ruleDel.m", method = RequestMethod.GET)
	public @ResponseBody DataTransObj ruleDel(ModelMap model,
			@RequestParam(value="id", required = true) String id) {
		
		ruleService.deleteById(id);
		return DataTransObj.onSuccess(null,"操作成功");
	}
	

}
