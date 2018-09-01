package com.metro.web.controller;

import java.util.Date;
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
import com.metro.model.Match;
import com.metro.model.MatchExample;
import com.metro.service.JobsService;
import com.metro.service.MatchService;
import com.metro.util.DateUtil;
import com.metro.util.SessionUtils;
import com.metro.vo.DataTransObj;

/***
 * 赛事控制
 * @author dell
 *
 */
@Controller
@RequestMapping(value="match")
public class MatchController  extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MatchController.class);
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	private MatchService matchService;
	
	
	/**
	 * 赛事列表
	 * 
	 */
	@RequestMapping(value = "matchlist.m", method = RequestMethod.GET)
	public String matchlist(ModelMap model) {
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);//岗位
		return "views/match-list.html";
	}
	
	/**
	 * 赛事列表数据
	 * 
	 */
	@RequestMapping(value = "matchSearch.m", method = RequestMethod.GET)
	public @ResponseBody DataTransObj matchSearch(
			@RequestParam(value="jobsId") String jobsId,
			@RequestParam(value="startNumber") Integer startNumber,
			@RequestParam(value="pageSize") Integer pageSize) {
		
		
		MatchExample example = new MatchExample();
		MatchExample.Criteria c = example.createCriteria();
		
		if(StringUtils.isNotBlank(jobsId)){
			c.andJobIdEqualTo(jobsId);
		}
		
		int totalNum = matchService.countByExample(example);
		
		//分页
		if(startNumber != null && pageSize != null){
			example.setLimitStart(startNumber);
			example.setLimitEnd(pageSize);
		}
		
		List<Match> list = matchService.selectByExample(example);
		for (Match match : list) {
			match.setJobsName(jobsService.getById(match.getJobId()).getJobsName());
		}
		return DataTransObj.onSuccess(list, "查询成功", totalNum);
	}
	
	/**
	 * 出题规则新建、修改页面
	 * 
	 */
	@RequestMapping(value = "matchSetInit.m", method = RequestMethod.GET)
	public String matchSetInit(ModelMap model,@RequestParam(value="id") String id) {
		if(StringUtils.isNotBlank(id)){
			model.put("match",matchService.getById(id));
		}
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);//岗位
		return "match-add.html";
	}
	
	
	/**
	 * 新建、修改出题规则
	 * 
	 */
	@RequestMapping(value = "matchSet.m", method = RequestMethod.POST)
	public @ResponseBody DataTransObj matchSet(
			@RequestParam(value="matchName") String matchName,
			@RequestParam(value="jobsId", required = true) String jobsId,
			@RequestParam(value="startDate", required = true) String startDate,
			@RequestParam(value="endDate", required = true) String endDate,
			@RequestParam(value="matchLevel", required = true) String matchLevel,
			@RequestParam(value="id") String id) {
		
		try {
			Match match = new Match();
			match.setId(id);
			match.setMatchName(matchName);
			match.setJobId(jobsId);
			match.setStartDate(DateUtil.strToDate(DateUtil.DATE_FORMAT_STA, startDate));
			match.setEndDate(DateUtil.strToDate(DateUtil.DATE_FORMAT_STA,endDate));
			match.setMatchLevel(matchLevel);
			match.setCreateTime(new Date());
			match.setOperater(SessionUtils.getLoginManager().getId());
			
			if(StringUtils.isNotBlank(id)){
				//更新
				matchService.update(match);
			}else{
				//新增
				matchService.insert(match);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return DataTransObj.onFailure(null,"操作失败");
		}
		return DataTransObj.onSuccess(null,"操作成功");
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "matchDel.m", method = RequestMethod.GET)
	public @ResponseBody DataTransObj matchDel(ModelMap model,
			@RequestParam(value="id", required = true) String id) {
		
		matchService.deleteById(id);
		return DataTransObj.onSuccess(null,"操作成功");
	}
	

}
