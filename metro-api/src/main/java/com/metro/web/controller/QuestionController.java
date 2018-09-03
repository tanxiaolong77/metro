package com.metro.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import com.metro.model.Answer;
import com.metro.model.AnswerExample;
import com.metro.model.Employee;
import com.metro.model.Match;
import com.metro.model.MatchExample;
import com.metro.model.MatchUserPass;
import com.metro.model.MatchUserPassExample;
import com.metro.model.Question;
import com.metro.model.QuestionExample;
import com.metro.model.Rule;
import com.metro.model.RuleExample;
import com.metro.model.UserQuestion;
import com.metro.model.UserQuestionExample;
import com.metro.service.AnswerService;
import com.metro.service.MatchService;
import com.metro.service.MatchUserPassService;
import com.metro.service.QuestionService;
import com.metro.service.RuleService;
import com.metro.service.UserQuestionService;
import com.metro.util.BaseUtil;
import com.metro.util.DateUtil;
import com.metro.util.SessionUtils;
import com.metro.vo.QuestionVo;

/***
 * 考题控制   
 */
@Controller
@RequestMapping(value="question")
public class QuestionController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private MatchUserPassService matchUserPassService;
	
	@Autowired
	private UserQuestionService userQuestionService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private AnswerService answerService;

	/***
	 * 出题目
	 * 1、技能类型，1:大赛；2:鉴定
	 * 2、当前时间是否开赛，否：return error();
	 * 3、是否有资格，否：return error();
	 * 4、出题，查用户题目表，是否在45分钟之内出过题，是：把出过的题目返回；否：出题逻辑
	 * @param model
	 * @param skillType		技能类型（1：技能大赛、2:技能鉴定）
	 * @param jobId			岗位类型（39种岗位）
	 * @param testType		考试类型（1：模拟、2：考试）
	 * @param matchLevel	赛制级别（1:初赛、2:复赛、3:决赛、4:总决赛）
	 */
	@RequestMapping(value = "toQuestion.u", method = RequestMethod.GET)
	public String doTest(ModelMap model) {
		
		Employee user = SessionUtils.getLoginUser();
		String userId = user.getId();
		String skillType = SessionUtils.getSkillType();
		String testType = SessionUtils.getTestType();
		String jobId = SessionUtils.getJobType();
		
		Date date = new Date();
		
		if (skillType.equals("1")) {
			logger.info("技能类型：技能大赛！！！");
			MatchExample matchExample = new MatchExample();
			MatchExample.Criteria matchCriteria = matchExample.createCriteria();
			if(StringUtils.isNotBlank(jobId)){
				matchCriteria.andJobIdEqualTo(jobId);
			}
			matchCriteria.andStartDateLessThanOrEqualTo(date);
			matchCriteria.andEndDateGreaterThan(date);
			List<Match> matchList = matchService.selectByExample(matchExample);
			
			if (matchList!=null && !matchList.isEmpty()) {
				logger.info("存在有效比赛！！！");
				Match match = matchList.get(0);
				
				// 判断用户是否有资格参加比赛
				MatchUserPassExample matchUserPassExample = new MatchUserPassExample();
				MatchUserPassExample.Criteria matchUserPassCriteria = matchUserPassExample.createCriteria();
				if(StringUtils.isNotBlank(userId)){
					matchUserPassCriteria.andUserIdEqualTo(userId);
				}
				if(StringUtils.isNotBlank(jobId)){
					matchUserPassCriteria.andJobIdEqualTo(jobId);
				}
				matchUserPassCriteria.andMatchIdEqualTo(match.getId());
				List<MatchUserPass> matchUserPassList = matchUserPassService.selectByExample(matchUserPassExample);
				
				if (matchUserPassList.size() == 0 && match.getMatchLevel().equals("1")) {
					logger.info("初次参赛，有资格参赛。");
					// 出题逻辑
					model = doQuestion(model,userId,date,skillType,testType,jobId,match.getId(),match.getMatchLevel());
					return "views/test.html";
				} else if (matchUserPassList.size() > 0 && !match.getMatchLevel().equals("1")
						&& (matchUserPassList.get(0).getMatchLevel() + 1).equals(match.getMatchLevel()) 
						&& matchUserPassList.get(0).getIsPass().equals("1")) {
					logger.info("上次比赛通过，此次比赛有资格。");
					// 出题逻辑
					model = doQuestion(model,userId,date,skillType,testType,jobId,match.getId(),match.getMatchLevel());
					return "views/test.html";
				} else {
					logger.info("没有资格参加比赛！！！");
					return error();
				}
				
			} else {
				logger.info("没有有效比赛！！！");
				return error();
			}
		}
		
		return "toskillType.u";
	}

	private ModelMap doQuestion(ModelMap model,String userId,Date date,String skillType,String testType,String jobId,
			String matchId,String matchLevel) {
		logger.info("开始出题！！！");
		
		List<Question>  questionList = new ArrayList<Question>();// 整套题
		
		List<Question>  questionList1 = new ArrayList<Question>();// 单选题
		List<Question>  questionList2 = new ArrayList<Question>();// 多选题
		List<Question>  questionList3 = new ArrayList<Question>();// 判断题
		
		// 查用户题目表
		UserQuestionExample userQuestionExample = new UserQuestionExample();
		UserQuestionExample.Criteria userQuestionCriteria = userQuestionExample.createCriteria();
		if(StringUtils.isNotBlank(userId)){
			userQuestionCriteria.andUserIdEqualTo(userId);
		}
		userQuestionCriteria.andStartTimeLessThanOrEqualTo(date);
		userQuestionCriteria.andEndTimeGreaterThan(date);
		if(StringUtils.isNotBlank(skillType)){
			userQuestionCriteria.andSkillTypeEqualTo(skillType);
		}
		if(StringUtils.isNotBlank(testType)){
			userQuestionCriteria.andTestTypeEqualTo(testType);
		}
		if(StringUtils.isNotBlank(jobId)){
			userQuestionCriteria.andJobsIdEqualTo(jobId);
		}
		if(StringUtils.isNotBlank(matchLevel)){
			userQuestionCriteria.andMatchLevelEqualTo(matchLevel);
		}
		List<UserQuestion> userQuestionList = userQuestionService.selectByExample(userQuestionExample);
		
		if (userQuestionList!=null && !userQuestionList.isEmpty()) {
			logger.info("45分钟之内出过题！！！");
			String questionId = userQuestionList.get(0).getQuestionId();
			String[] questions = questionId.split(",");
			ArrayList<String> questionIdList = (ArrayList<String>) Arrays.asList(questions);
			QuestionExample questionExample = new QuestionExample();
			QuestionExample.Criteria questionCriteria = questionExample.createCriteria();
			questionCriteria.andIdIn(questionIdList);
			questionList = questionService.selectByExample(questionExample);
			model.put("questionList", questionList);
			
			// 结束时间
			UserQuestionExample userQuestionExample1 = new UserQuestionExample();
			UserQuestionExample.Criteria userQuestionCriteria1 = userQuestionExample1.createCriteria();
			if(StringUtils.isNotBlank(userId)){
				userQuestionCriteria1.andUserIdEqualTo(userId);
			}
			userQuestionCriteria.andCreateTimeEqualTo(date);
			List<UserQuestion> userQuestionList1 = userQuestionService.selectByExample(userQuestionExample);
			UserQuestion userQuestion = userQuestionList1.get(0);
			String time = DateUtil.formatDate(userQuestion.getEndTime());
			logger.info("考试结束时间：" + time);
			model.put("time", time);
		
		} else {
			logger.info("45分钟之内没出过题！！！");
			// 出题规则（岗位、知识点、题型、题量）
			QuestionVo questionVo = new QuestionVo();
			if(StringUtils.isNotBlank(jobId)){
				questionVo.setJobsId(jobId);
			}
			 
			logger.info("根据岗位，查看出题规则，岗位类型：" + jobId);
			RuleExample ruleExample = new RuleExample();
			RuleExample.Criteria ruleCriteria = ruleExample.createCriteria();
			if(StringUtils.isNotBlank(jobId)){
				ruleCriteria.andJobIdEqualTo(jobId);
			}
			List<Rule> ruleList = ruleService.selectByExample(ruleExample);
			Rule rule = ruleList.get(0);
			String contentType = rule.getContentType();
			questionVo.setContentType(contentType);
			
			// 每一个知识点题量
			int count = Integer.parseInt(rule.getContentRate());
			
			// 单选题量
			int oneChoose = (int) (count * (Double.valueOf(rule.getOneChoose()).doubleValue() / 100));
			questionVo.setQuestionType("1");
			questionVo.setCount(oneChoose);
			// 单选出题
			// 题目
			List<Question> questionOneList = questionService.selectByQuestionVo(questionVo);
			for (int i = 0; i < questionOneList.size(); i++) {
				Question questionOne = questionOneList.get(i);
				String questionId = questionOne.getId();
				AnswerExample answerExample = new AnswerExample();
				AnswerExample.Criteria answerCriteria = answerExample.createCriteria();
				answerCriteria.andQuestionIdEqualTo(questionId);
				// 答案
				List<Answer> answerList = answerService.selectByExample(answerExample);
				questionOne.setAnswers(answerList);
				questionOneList.add(questionOne);
			}
			questionList1.addAll(questionOneList);
			
			
			// 多选题量
			int manyChoose = (int) (count * (Double.valueOf(rule.getManyChoose()).doubleValue() / 100));
			questionVo.setQuestionType("2");
			questionVo.setCount(manyChoose);
			// 多选出题
			// 题目
			List<Question> questionManyList = questionService.selectByQuestionVo(questionVo);
			for (int i = 0; i < questionManyList.size(); i++) {
				Question questionMany = questionManyList.get(i);
				String questionId = questionMany.getId();
				AnswerExample answerExample = new AnswerExample();
				AnswerExample.Criteria answerCriteria = answerExample.createCriteria();
				answerCriteria.andQuestionIdEqualTo(questionId);
				// 答案
				List<Answer> answerList = answerService.selectByExample(answerExample);
				questionMany.setAnswers(answerList);
				questionManyList.add(questionMany);
			}
			questionList2.addAll(questionManyList);
			
			// 判断题量
			int judge = (int) (count * (Double.valueOf(rule.getJudge()).doubleValue() / 100));
			questionVo.setQuestionType("3");
			questionVo.setCount(judge);
			// 判断出题
			// 题目
			List<Question> questionJudgeList = questionService.selectByQuestionVo(questionVo);
			for (int i = 0; i < questionJudgeList.size(); i++) {
				Question questionJudge = questionJudgeList.get(i);
				String questionId = questionJudge.getId();
				AnswerExample answerExample = new AnswerExample();
				AnswerExample.Criteria answerCriteria = answerExample.createCriteria();
				answerCriteria.andQuestionIdEqualTo(questionId);
				// 答案
				List<Answer> answerList = answerService.selectByExample(answerExample);
				questionJudge.setAnswers(answerList);
				questionJudgeList.add(questionJudge);
			}
			questionList2.addAll(questionJudgeList);
			
			// 打乱不同知识点题目顺序
			Collections.shuffle(questionList1);
			Collections.shuffle(questionList2);
			Collections.shuffle(questionList3);
			
			model.put("questionList1", questionList1);// 单选
			model.put("questionList2", questionList2);// 多选
			model.put("questionList3", questionList3);// 判断
			
			// 考试时间：45分钟
			String time = "";
			time = DateUtil.formatDate(DateUtil.addMinutesToDate(date, 45));
			logger.info("考试结束时间：" + time);
			model.put("time", time);
			
			// 入用户题目表
			String questionId = "";
			for (int i = 0; i < questionList1.size(); i++) {
				questionId += questionList1.get(i).getId() + ",";
			}
			for (int j = 0; j < questionList2.size(); j++) {
				questionId += questionList2.get(j).getId() + ",";
			}
			for (int k = 0; k < questionList3.size(); k++) {
				questionId += questionList3.get(k).getId() + ",";
			}
			UserQuestion userQuestion = new UserQuestion();
			userQuestion.setId(BaseUtil.getUUID());
			userQuestion.setUserId(userId);
			userQuestion.setJobsId(jobId);
			userQuestion.setMatchLevel(matchLevel);
			userQuestion.setSkillType(skillType);
			userQuestion.setTestType(testType);
			userQuestion.setQuestionId(questionId);
			userQuestion.setCreateTime(date);
			userQuestion.setStartTime(date);
			userQuestion.setEndTime(DateUtil.formatDateStr(time));
			userQuestionService.insert(userQuestion);
			
			// 入用户比赛晋级表
			MatchUserPass matchUserPass = new MatchUserPass();
			matchUserPass.setId(BaseUtil.getUUID());
			matchUserPass.setUserId(userId);
			matchUserPass.setJobId(jobId);
			matchUserPass.setMatchLevel(matchLevel);
			matchUserPass.setCreateTime(new Date());
			matchUserPass.setIsPass("0");
			matchUserPassService.insert(matchUserPass);
			
		}
		
		return model;
	}

}
		
	

