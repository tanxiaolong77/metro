package com.metro.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.metro.common.constant.Constant;
import com.metro.model.Jobs;
import com.metro.model.JobsExample;
import com.metro.model.Match;
import com.metro.model.MatchExample;
import com.metro.model.Score;
import com.metro.model.ScoreExample;
import com.metro.service.JobsService;
import com.metro.service.MatchService;
import com.metro.service.ScoreService;
import com.metro.util.SessionUtils;

/**
 * 跳转控制
 */
@Controller
@RequestMapping(value="skip")
public class SkipController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(SkipController.class);
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	ScoreService scoreService;
	
	/**
	 * 技能大赛跳转到考试类型页面
	 * 
	 */
	@RequestMapping(value = "toTestType.u", method = RequestMethod.GET)
	public String toTestType(ModelMap model,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String skillType = "1";
		session.setAttribute(Constant.SKILL_TYPE, skillType);
		
		return "views/test-type.html";
	}
	
	/**
	 * 模拟跳转到岗位类型页面
	 * 
	 */
	@RequestMapping(value = "toSeemJobType.u", method = RequestMethod.GET)
	public String toSeemJobType(ModelMap model,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String testType = "1";
		session.setAttribute(Constant.TEST_TYPE, testType);
		
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);// 岗位
		
		return "views/job-type.html";
	}
	
	/**
	 * 考试跳转到岗位类型页面
	 * 
	 */
	@RequestMapping(value = "toTestJobType.u", method = RequestMethod.GET)
	public String toMatchJobType(ModelMap model,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String testType = "2";
		session.setAttribute(Constant.TEST_TYPE, testType);
		
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);// 岗位
		
		return "views/job-type.html";
	}
	
	
	/**
	 * 跳转到赛程页面
	 * 
	 */
	@RequestMapping(value = "toMatchType.u", method = RequestMethod.GET)
	public String toMatchType(ModelMap model,@RequestParam("jobId") String jobId,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.setAttribute(Constant.JOB_TYPE, jobId);
		
		String testType = SessionUtils.getTestType();
		// 模拟，直接出题
		if ("1".equals(testType)) {
			return "redirect:/question/toQuestion.u";
		// 考试，选择赛程
		} else if ("2".equals(testType)) {
			Date date = new Date();
			MatchExample matchExample = new MatchExample();
			MatchExample.Criteria matchCriteria = matchExample.createCriteria();
			if(StringUtils.isNotBlank(jobId)){
				matchCriteria.andJobIdEqualTo(jobId);
			}
			matchCriteria.andStartDateLessThanOrEqualTo(date);
			matchCriteria.andEndDateGreaterThan(date);
			List<Match> matchList = matchService.selectByExample(matchExample);
			
			if (matchList!=null && !matchList.isEmpty()) {
				Match match = matchList.get(0);
				logger.info("正在比赛的级别：" + match.getMatchLevel());
				if (match.getMatchLevel() != null) {
					
					session.setAttribute(Constant.MATCH_LEVEL, match.getMatchLevel());
					session.setAttribute(Constant.MATCH_ID, match.getId());
					
					model.put("matchLevel", match.getMatchLevel());// 赛程
				}
			} else {
				logger.info("不存在有效比赛！！！");
				model.put("matchLevel", "0");// 没有比赛
			}
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
