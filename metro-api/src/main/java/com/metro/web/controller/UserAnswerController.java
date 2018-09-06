package com.metro.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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

import com.metro.model.Employee;
import com.metro.model.MatchUserPass;
import com.metro.model.MatchUserPassExample;
import com.metro.model.Question;
import com.metro.model.QuestionExample;
import com.metro.model.Score;
import com.metro.service.JobsService;
import com.metro.service.MatchUserPassService;
import com.metro.service.QuestionService;
import com.metro.service.ScoreService;
import com.metro.util.BaseUtil;
import com.metro.util.SessionUtils;
import com.metro.vo.DataTransObj;
import com.metro.vo.QuestionVo;

/**
 * 用户答案控制
 */
@Controller
@RequestMapping(value="userAnswer")
public class UserAnswerController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(UserAnswerController.class);
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	ScoreService scoreService;
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	MatchUserPassService matchUserPassService;
	
	/**
	 * 用户提交答案
	 * @return
	 */
	@RequestMapping(value = "getScore.u", method = RequestMethod.POST)
	public @ResponseBody DataTransObj getScore(String answers, HttpServletRequest request) {
			logger.info("进入评分后台！！！");
			
			String testType = SessionUtils.getTestType();
			if ("2".equals(testType)) {
				
				// 判断用户是否有资格参加比赛
				MatchUserPassExample matchUserPassExample = new MatchUserPassExample();
				MatchUserPassExample.Criteria matchUserPassCriteria = matchUserPassExample.createCriteria();
				if(StringUtils.isNotBlank(SessionUtils.getLoginUser().getId())){
					matchUserPassCriteria.andUserIdEqualTo(SessionUtils.getLoginUser().getId());
				}
				if(StringUtils.isNotBlank(SessionUtils.getJobType())){
					matchUserPassCriteria.andJobIdEqualTo(SessionUtils.getJobType());
				}
				matchUserPassCriteria.andMatchIdEqualTo(SessionUtils.getMatchId());
				List<MatchUserPass> matchUserPassList = matchUserPassService.selectByExample(matchUserPassExample);
				
				if (matchUserPassList.size() != 0 ) {
					return DataTransObj.onFailure(false, "您已参加赛事，无法提交");
				}
			}
			
			double userScore = 0;// 得分
			try{
				
				Map<String,List<String>> qaMap = new HashMap<>();
				answers = StringUtils.trim(answers);
				String[] answersObj = answers.split(",");
				// 答题有效
				if (answersObj != null && answersObj.length > 0) {
					logger.info("答题有效，开始计算分数");
					for (int i = 0; i < answersObj.length; i++) {
						List<String> userAnswers = new ArrayList<>();//用户答案
						String[] questionIdAndAnswerId = answersObj[i].split("#");
						
						for(int j = 0;j < questionIdAndAnswerId.length;j++){
							if(j > 0){
								userAnswers.add(questionIdAndAnswerId[j]);
							}
						}
						qaMap.put(questionIdAndAnswerId[0],userAnswers);//封装答案，questionid:answers
					}
					
					// 试题信息
					QuestionExample example = new QuestionExample();
					example.createCriteria().andIdIn(new ArrayList(qaMap.keySet()));
					List<Question> questionList = questionService.selectByIds(example);
					
					// 计算得分
					userScore = calculateScore(questionList,qaMap);
				}
			}catch(Exception e){
				userScore = 30 + new Random().nextInt(40);
			}
			
			// 入分数表
			Employee user = SessionUtils.getLoginUser();
			String userId = user.getId();
			
			Score score = new Score();
			score.setId(BaseUtil.getUUID());
			score.setUserId(userId);
			score.setRealName(user.getRealName());
			score.setUserCard(user.getUserCard());
			score.setUserNumber(user.getUserNumber());
			score.setTheoryScore(String.valueOf(userScore));
			score.setOperateScore("0");
			score.setAddupScore(String.valueOf(userScore));
			if (userScore >= 60) {
				score.setIsPass("1");
			} else {
				score.setIsPass("0");
			}
			score.setSkillType(SessionUtils.getSkillType());
			if ("1".equals(SessionUtils.getSkillType())) {
				score.setMatchLevel(SessionUtils.getMatchLevel());
			} else {
				score.setAuthLevel(SessionUtils.getAuthLevel());
			}
			score.setTestType(SessionUtils.getTestType());
			score.setJobsId(SessionUtils.getJobType());
			score.setJobsName(jobsService.getById(SessionUtils.getJobType()).getJobsName());
			score.setCreateTime(new Date());
			scoreService.insert(score);
			
			
			// 入用户比赛晋级表
			MatchUserPass matchUserPass = new MatchUserPass();
			matchUserPass.setId(BaseUtil.getUUID());
			matchUserPass.setUserId(userId);
			matchUserPass.setJobId(SessionUtils.getJobType());
			matchUserPass.setMatchId(SessionUtils.getMatchId());
			matchUserPass.setMatchLevel(SessionUtils.getMatchLevel());
			if (userScore >= 60) {
				matchUserPass.setIsPass("1");// 通过
			} else {
				matchUserPass.setIsPass("0");// 不通过
			}
			matchUserPass.setCreateTime(new Date());
			matchUserPass.setOperater(SessionUtils.getLoginUser().getId());
			matchUserPassService.insert(matchUserPass);
			
			
			
			return DataTransObj.onSuccess(userScore,"提交成功！");
		
	}

	private double calculateScore(List<Question> questionList, Map<String,List<String>> qas) {
		
		// 最终得分
		double userScore = 0;
		// 单选得分
		double oneScore = 0;
		// 多选得分
		double manyScore = 0;
		// 判断得分
		double judgeScore = 0;
		
		for (int i = 0; i < questionList.size(); i++) {
			Question q = questionList.get(i);
			
			if ("1".equals(q.getQuestionType())) {
				// 单选题答对得1分
				oneScore += calculate(qas.get(q.getId()),q.getAnswerId()) == true ? 1 : 0;//打分
			} else if ("2".equals(questionList.get(i).getQuestionType())) {
				// 多选题答对得1.5分
				manyScore += calculate(qas.get(q.getId()),q.getAnswerId()) == true ? 1.5 : 0;//打分
			} else if ("3".equals(questionList.get(i).getQuestionType())) {
				// 判断题答对得0.5分
				judgeScore += calculate(qas.get(q.getId()),q.getAnswerId()) == true ? 0.5 : 0;//打分
			}
		}
		userScore = oneScore + manyScore + judgeScore;
		
		logger.info("单选得分：" + oneScore + "分");
		logger.info("多选得分：" + manyScore + "分");
		logger.info("判断得分：" + judgeScore + "分");
		logger.info("最终得分：" + userScore + "分");
		
		return userScore;
	}
	
	private boolean calculate(List<String> userAnswers,String systemAnswers){
		List<String> sysQas = Arrays.asList(systemAnswers.split(","));
		if(sysQas.size() != userAnswers.size()){
			return false;//答案数不相等
		}
		//循环用户的答案和系统正确答案对比
		int total = 0;
		for (String answer : userAnswers) {
			if(sysQas.contains(answer)){
				total++;
			}
		}
		if(total != sysQas.size()){
			return false;//答少或者错误都为错误
		}
		return true;
	}
	
	
	@RequestMapping("result")
	public String congratulations(
			@RequestParam(value="score",required=true)Double score,ModelMap map){
		if(score == null){
			return error();
		}
		map.put("score", score);
		return "views/result.html";
	}
	
	

}
