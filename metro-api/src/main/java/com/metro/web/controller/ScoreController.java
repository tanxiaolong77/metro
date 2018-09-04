package com.metro.web.controller;

import java.math.BigDecimal;
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
import com.metro.model.Score;
import com.metro.model.ScoreExample;
import com.metro.request.ScoreRequest;
import com.metro.service.JobsService;
import com.metro.service.ScoreService;
import com.metro.util.BeanUtils;
import com.metro.util.DateUtil;
import com.metro.util.SessionUtils;
import com.metro.vo.DataTransObj;
import com.metro.vo.ScoreVO;

/***
 * 成绩查询控制
 * @author dell
 *
 */
@Controller
@RequestMapping(value="score")
public class ScoreController  extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(ScoreController.class);
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	private ScoreService scoreService;
	
	
	/**
	 * 分数列表
	 * 
	 */
	@RequestMapping(value = "scorelist.m", method = RequestMethod.GET)
	public String matchlist(ModelMap model) {
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);//岗位
		return "views/sys-score-list.html";
	}
	
	/**
	 * 分数列表数据
	 * 
	 */
	@RequestMapping(value = "scoreSearch.m", method = RequestMethod.GET)
	public @ResponseBody DataTransObj matchSearch(
			ScoreRequest request) {
		
		
		ScoreExample example = new ScoreExample();
		ScoreExample.Criteria c = example.createCriteria();
		
		//姓名
		if(StringUtils.isNotBlank(request.getRealName())){
			c.andRealNameLike("%"+request.getRealName()+"%");
		}
		
		//身份证
		if(StringUtils.isNotBlank(request.getUserCard())){
			c.andUserCardLike("%"+request.getUserCard()+"%");
		}
		
		//岗位
		if(StringUtils.isNotBlank(request.getJobsId()) && !"#".equals(request.getJobsId())){
			c.andJobsIdEqualTo(request.getJobsId());
		}
		
		//技能类型
		if(StringUtils.isNotBlank(request.getSkillType()) && !"#".equals(request.getSkillType())){
			c.andSkillTypeEqualTo(request.getSkillType());
		}
		
		//考试类型
		if(StringUtils.isNotBlank(request.getTestType()) && !"#".equals(request.getTestType())){
			c.andTestTypeEqualTo(request.getTestType());
		}
		
		//理论分大于&小于
		if(StringUtils.isNotBlank(request.getTheoryScoreGt())
				&& StringUtils.isNumeric(request.getTheoryScoreGt())){
			c.andTheoryScoreGreaterThanOrEqualTo(request.getTheoryScoreGt());
		}
		if(StringUtils.isNotBlank(request.getTheoryScoreLt())
				&& StringUtils.isNumeric(request.getTheoryScoreLt())){
			c.andTheoryScoreLessThanOrEqualTo(request.getTheoryScoreLt());
		}
		
		//操作分大于&小于
		if(StringUtils.isNotBlank(request.getOperateScoreGt())
				&& StringUtils.isNumeric(request.getOperateScoreGt())){
			c.andOperateScoreGreaterThanOrEqualTo(request.getOperateScoreGt());
		}
		if(StringUtils.isNotBlank(request.getOperateScoreLt())
				&& StringUtils.isNumeric(request.getOperateScoreLt())){
			c.andOperaterLessThanOrEqualTo(request.getOperateScoreLt());
		}
		
		//总分大于&小于
		if(StringUtils.isNotBlank(request.getAddupScoreGt())
				&& StringUtils.isNumeric(request.getAddupScoreGt())){
			c.andAddupScoreGreaterThanOrEqualTo(request.getAddupScoreGt());
		}
		if(StringUtils.isNotBlank(request.getAddupScoreLt())
				&& StringUtils.isNumeric(request.getAddupScoreLt())){
			c.andAddupScoreLessThanOrEqualTo(request.getAddupScoreLt());
		}
		
		//评分日期
		if(StringUtils.isNotBlank(request.getStartDate())
				&& StringUtils.isNotBlank(request.getEnDate())){
			String startDate = request.getStartDate()+" 00:00:00";
			String endDate = request.getEnDate()+" 23:59:59";
			c.andCreateTimeBetween(
					DateUtil.strToDate(DateUtil.DATE_FORMAT_LONG, startDate), 
					DateUtil.strToDate(DateUtil.DATE_FORMAT_LONG, endDate));
		}
		
		int totalNum = scoreService.countByExample(example);
		
		//分页
		if(request.getStartNumber() != null){
			example.setStartNumber(request.getStartNumber());
		}
		example.setOrderByClause("create_time desc");
		List<Score> list = scoreService.selectByExample(example);
		
		return DataTransObj.onSuccess(BeanUtils.transfersB(list, ScoreVO.class), "查询成功", totalNum);
	}
	
	/**
	 * score修改页面（只能补填操作分数）
	 * 
	 */
	@RequestMapping(value = "scoreSetInit.m", method = RequestMethod.GET)
	public String scoreSetInit(ModelMap model,
			@RequestParam(value="id", required = true) String id) {

		Score score = scoreService.getById(id);
				
		model.put("score", score);//岗位
		return "views/sys-score-add.html";
	}
	
	
	/**
	 * 补填操作分数
	 * 
	 */
	@RequestMapping(value = "scoreSet.m", method = RequestMethod.POST)
	public @ResponseBody DataTransObj scoreSet(
			String matchName,
			@RequestParam(value="operateScore", required = true) String operateScore,
			@RequestParam(value="id", required = true) String id) {
		
		try {
			if(!StringUtils.isNumeric(operateScore)){
				return DataTransObj.onFailure(null,"金额必须为数字");
			}
			//修改操作分并且增加总分
			Score score = scoreService.getById(id);
			String oldAddupScore = score.getAddupScore();
			BigDecimal newAddupScore;
			if(StringUtils.isBlank(oldAddupScore)){
				newAddupScore = new BigDecimal(operateScore);
			}else{
				//在修改总分之前先扣减之前的操作分数再加
				newAddupScore = new BigDecimal(score.getTheoryScore()).add(new BigDecimal(operateScore));
				if(newAddupScore.compareTo(new BigDecimal(100)) > 0){
					return DataTransObj.onFailure(null,"操作分加理论分不得超过100分");
				}
			}
			Score updateScore = new Score();
			updateScore.setId(id);
			updateScore.setAddupScore(newAddupScore.toString());
			updateScore.setOperateScore(operateScore);
			updateScore.setUpdateTime(new Date());
			updateScore.setModifier(SessionUtils.getLoginManager().getId());
			scoreService.update(updateScore);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return DataTransObj.onFailure(null,"操作失败");
		}
		return DataTransObj.onSuccess(null,"操作成功");
	}

}
