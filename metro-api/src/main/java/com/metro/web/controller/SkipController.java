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

import com.metro.model.Jobs;
import com.metro.model.JobsExample;
import com.metro.model.Match;
import com.metro.model.MatchExample;
import com.metro.model.Score;
import com.metro.model.ScoreExample;
import com.metro.service.JobsService;
import com.metro.service.MatchService;
import com.metro.service.ScoreService;

/**
 * 跳转控制
 */
@Controller
@RequestMapping(value="skip")
public class SkipController  extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MatchController.class);
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	ScoreService scoreService;
	
	
	/**
	 * 跳转到岗位类型页面
	 * 
	 */
	@RequestMapping(value = "toJobType.u", method = RequestMethod.GET)
	public String toJobType(ModelMap model) {
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);//岗位
		return "views/job-type.html";
	}
	
	
	/**
	 * 跳转到赛程页面
	 * 
	 */
	@RequestMapping(value = "toMatchType.u", method = RequestMethod.GET)
	public String toMatchType(ModelMap model,@RequestParam("jobId") String jobId) {
		
		Date date = new Date();
		MatchExample matchExample = new MatchExample();
		MatchExample.Criteria matchCriteria = matchExample.createCriteria();
		if(StringUtils.isNotBlank(jobId)){
			matchCriteria.andJobIdEqualTo(jobId);
		}
		matchCriteria.andStartDateLessThanOrEqualTo(date);
		matchCriteria.andEndDateGreaterThan(date);
		List<Match> matchList = matchService.selectByExample(matchExample);
		Match match = matchList.get(0);
		
		if (match != null) {
			logger.info("存在有效比赛！！！");
			model.put("matchLevel", match.getMatchLevel());// 赛程
		} else {
			logger.info("不存在有效比赛！！！");
			model.put("matchLevel", "0");// 没有比赛
		}
		
		return "views/match-type.html";
	}
	
	/**
	 * 跳转到评分页面
	 * 
	 */
	@RequestMapping(value = "toScore.u", method = RequestMethod.GET)
	public String toScore(ModelMap model) {
		List<Score> score = scoreService.selectByExample(new ScoreExample());
		model.put("score", score);// 分数
		return "views/score.html";
	}
	
	
}
