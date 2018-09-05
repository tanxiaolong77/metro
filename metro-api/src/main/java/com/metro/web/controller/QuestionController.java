package com.metro.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.metro.model.Answer;
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
		
		if ("1".equals(skillType)) {
			logger.info("技能类型：技能大赛！！！");
			if ("1".equals(testType)) {
				logger.info("考试类型：模拟！！！");
				// 出题逻辑
				model = doSeemQuestion(model,userId,date,skillType,testType,jobId,45*60);
				return "views/test.html";
				
			} else {
				logger.info("考试类型：考试！！！");
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
						model = doTestQuestion(model,userId,date,skillType,testType,jobId,match.getId(),match.getMatchLevel());
						return "views/test.html";
					} else if (matchUserPassList.size() > 0 && !match.getMatchLevel().equals("1")
							&& (matchUserPassList.get(0).getMatchLevel() + 1).equals(match.getMatchLevel()) 
							&& matchUserPassList.get(0).getIsPass().equals("1")) {
						logger.info("上次比赛通过，此次比赛有资格。");
						// 出题逻辑
						model = doTestQuestion(model,userId,date,skillType,testType,jobId,match.getId(),match.getMatchLevel());
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
		}
		
		return "toskillType.u";
	}

	private ModelMap doSeemQuestion(ModelMap model, String userId, Date date, String skillType, String testType,
			String jobId,int expire) {
		logger.info("开始出题！！！");
		
		List<QuestionVo>  questionList1 = new ArrayList<QuestionVo>();// 单选题
		List<QuestionVo>  questionList2 = new ArrayList<QuestionVo>();// 多选题
		List<QuestionVo>  questionList3 = new ArrayList<QuestionVo>();// 判断题
		
		logger.info("查出某一岗位、某一知识点的全部题目及答案，岗位类型：" + jobId);
		QuestionVo questionVo = new QuestionVo();
		if(StringUtils.isNotBlank(jobId)){
			questionVo.setJobsId(jobId);
		}
		List<QuestionVo> questionList = questionService.selectByQuestionVo(questionVo);
		model.put("jobsName", questionList.get(0).getJobsName());
		Map<String,List<QuestionVo>> questionMap = new HashMap<>();
		// 打乱顺序
		Collections.shuffle(questionList);
		//根据contentType封装
		for (QuestionVo q : questionList) {
			
			if(questionMap.get(q.getContentType()) == null){
				List<QuestionVo> tmpList = new ArrayList<>();
				questionMap.put(q.getContentType(),tmpList);
			}
			
			if (!"3".equals(q.getQuestionType())) {
				List<Answer> answerList = new ArrayList<Answer>();
				
				String[] answerObj = q.getAnswerObj().split("#row#");
				if (answerObj != null && answerObj.length > 0) {
					for (int k = 0; k < answerObj.length; k++) {
						Answer answer = new Answer();
						String[] answers = answerObj[k].split("#col#");
						String answerId = answers[0];
						if(StringUtils.isNotBlank(answerId) && answerId.startsWith(",")){
							answerId = answerId.substring(1);
						}
						String answerDesc = answers[1];
						answer.setId(answerId);
						answer.setAnswerDesc(answerDesc);
						if (answers.length > 2) {
							answer.setAnswerImage(answers[2].substring(1));
						}
						answerList.add(answer);
					}
				}
				q.setAnswerList(answerList);
			}
			
			questionMap.get(q.getContentType()).add(q);
		}
		
		RuleExample ruleExample = new RuleExample();
		RuleExample.Criteria ruleCriteria = ruleExample.createCriteria();
		if(StringUtils.isNotBlank(jobId)){
			ruleCriteria.andJobIdEqualTo(jobId);
		}
		List<Rule> ruleList = ruleService.selectByExample(ruleExample);
		for (int i = 0; i < ruleList.size(); i++ ) {
			Rule rule = ruleList.get(i);
			int count = Integer.parseInt(rule.getContentRate());
			int oneChoose = (int) (count * (Double.valueOf(rule.getOneChoose()).doubleValue() / 100));
			int manyChoose = (int) (count * (Double.valueOf(rule.getManyChoose()).doubleValue() / 100));
			int judge = (int) (count * (Double.valueOf(rule.getJudge()).doubleValue() / 100));
			
			List<QuestionVo> contentTypeDatas = questionMap.get(rule.getContentType());
			questionList1.addAll(findQuestion(contentTypeDatas, oneChoose,"1"));
			questionList2.addAll(findQuestion(contentTypeDatas, manyChoose,"2"));
			questionList3.addAll(findQuestion(contentTypeDatas, judge,"3"));
		}
		
		model.put("questionList1", questionList1);// 单选
		model.put("questionList2", questionList2);// 多选
		model.put("questionList3", questionList3);// 判断
		int order = 0;
		String[] abcdefghijk = {"A","B","C","D","E","F","G","H","I","J","K"};
		//单选
		String questions = "";
		for (QuestionVo q : questionList1) {
			List<Answer> answers = q.getAnswerList();
			for (int i = 0; i < answers.size(); i++) {
				answers.get(i).setTmp(abcdefghijk[i]);
			}
			q.setOrder(++order);
			questions += ","+q.getId();
		}
		//多选
		for (QuestionVo q : questionList2) {
			List<Answer> answers = q.getAnswerList();
			for (int i = 0; i < answers.size(); i++) {
				answers.get(i).setTmp(abcdefghijk[i]);
			}
			q.setOrder(++order);
			questions += ","+q.getId();
		}
		//判断
		for (QuestionVo q : questionList3) {
			q.setOrder(++order);
			questions += ","+q.getId();
		}
		
		// 模拟考试时间：45分钟
		logger.info("模拟考试结束时间：" + expire);
		
		model.put("expire", expire);
		model.put("questions", questions.substring(1));
		return model;
	}
	
	
	private List<QuestionVo> findQuestion(List<QuestionVo> vos,int max,String questionType){
		List<QuestionVo> results = new ArrayList<>();
		for (QuestionVo questionVo : vos) {
			if(results.size() == max){
				break;
			}
			if(questionType.equals(questionVo.getQuestionType())){
				results.add(questionVo);
			}
		}
		return results;
	}
	

	private ModelMap doTestQuestion(ModelMap model,String userId,Date date,String skillType,String testType,String jobId,
			String matchId,String matchLevel) {
		logger.info("开始出题！！！");
		
		List<QuestionVo>  questionListbefore = new ArrayList<QuestionVo>();// 整套题
		
		List<QuestionVo>  questionList1 = new ArrayList<QuestionVo>();// 单选题
		List<QuestionVo>  questionList2 = new ArrayList<QuestionVo>();// 多选题
		List<QuestionVo>  questionList3 = new ArrayList<QuestionVo>();// 判断题
		
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
			questionListbefore = questionService.selectByQuestionId(questionIdList);
			model.put("jobsName", questionListbefore.get(0).getJobsName());
			Map<String,List<QuestionVo>> questionMap = new HashMap<>();
			//根据contentType封装
			for (QuestionVo q : questionListbefore) {
				
				if(questionMap.get(q.getContentType()) == null){
					List<QuestionVo> tmpList = new ArrayList<>();
					questionMap.put(q.getContentType(),tmpList);
				}
				
				if (!"3".equals(q.getQuestionType())) {
					List<Answer> answerList = new ArrayList<Answer>();
					
					String[] answerObj = q.getAnswerObj().split("#row#");
					if (answerObj != null && answerObj.length > 0) {
						for (int k = 0; k < answerObj.length; k++) {
							Answer answer = new Answer();
							String[] answers = answerObj[k].split("#col#");
							String answerId = answers[0];
							if(StringUtils.isNotBlank(answerId) && answerId.startsWith(",")){
								answerId = answerId.substring(1);
							}
							String answerDesc = answers[1];
							answer.setId(answerId);
							answer.setAnswerDesc(answerDesc);
							if (answers.length > 2) {
								answer.setAnswerImage(answers[2].substring(1));
							}
							answerList.add(answer);
						}
					}
					q.setAnswerList(answerList);
				}
				
				questionMap.get(q.getContentType()).add(q);
			}
			
			logger.info("根据岗位，查看出题规则，岗位类型：" + jobId);
			RuleExample ruleExample = new RuleExample();
			RuleExample.Criteria ruleCriteria = ruleExample.createCriteria();
			if(StringUtils.isNotBlank(jobId)){
				ruleCriteria.andJobIdEqualTo(jobId);
			}
			List<Rule> ruleList = ruleService.selectByExample(ruleExample);
			for (int i = 0; i < ruleList.size(); i++ ) {
				Rule rule = ruleList.get(i);
				int count = Integer.parseInt(rule.getContentRate());
				int oneChoose = (int) (count * (Double.valueOf(rule.getOneChoose()).doubleValue() / 100));
				int manyChoose = (int) (count * (Double.valueOf(rule.getManyChoose()).doubleValue() / 100));
				int judge = (int) (count * (Double.valueOf(rule.getJudge()).doubleValue() / 100));
				
				List<QuestionVo> contentTypeDatas = questionMap.get(rule.getContentType());
				questionList1.addAll(findQuestion(contentTypeDatas, oneChoose,"1"));
				questionList2.addAll(findQuestion(contentTypeDatas, manyChoose,"2"));
				questionList3.addAll(findQuestion(contentTypeDatas, judge,"3"));
			}
			
			model.put("questionList1", questionList1);// 单选
			model.put("questionList2", questionList2);// 多选
			model.put("questionList3", questionList3);// 判断
			
			// 结束时间
			UserQuestionExample userQuestionExample1 = new UserQuestionExample();
			UserQuestionExample.Criteria userQuestionCriteria1 = userQuestionExample1.createCriteria();
			if(StringUtils.isNotBlank(userId)){
				userQuestionCriteria1.andUserIdEqualTo(userId);
			}
			userQuestionCriteria.andCreateTimeEqualTo(date);
			List<UserQuestion> userQuestionList1 = userQuestionService.selectByExample(userQuestionExample);
			UserQuestion userQuestion = userQuestionList1.get(0);
			
			String time = DateUtil.getEndTime(new Date(),userQuestion.getEndTime());
			logger.info("考试结束时间：" + time);
			model.put("time", time);
		
		} else {
			logger.info("45分钟之内没出过题！！！");
			logger.info("查出某一岗位、某一知识点的全部题目及答案，岗位类型：" + jobId);
			QuestionVo questionVo = new QuestionVo();
			if(StringUtils.isNotBlank(jobId)){
				questionVo.setJobsId(jobId);
			}
			List<QuestionVo> questionList = questionService.selectByQuestionVo(questionVo);
			model.put("jobsName", questionList.get(0).getJobsName());
			Map<String,List<QuestionVo>> questionMap = new HashMap<>();
			// 打乱顺序
			Collections.shuffle(questionList);
			
			//根据contentType封装
			for (QuestionVo q : questionList) {
				
				if(questionMap.get(q.getContentType()) == null){
					List<QuestionVo> tmpList = new ArrayList<>();
					questionMap.put(q.getContentType(),tmpList);
				}
				
				if (!"3".equals(q.getQuestionType())) {
					List<Answer> answerList = new ArrayList<Answer>();
					
					String[] answerObj = q.getAnswerObj().split("#row#");
					if (answerObj != null && answerObj.length > 0) {
						for (int k = 0; k < answerObj.length; k++) {
							Answer answer = new Answer();
							String[] answers = answerObj[k].split("#col#");
							String answerId = answers[0];
							if(StringUtils.isNotBlank(answerId) && answerId.startsWith(",")){
								answerId = answerId.substring(1);
							}
							String answerDesc = answers[1];
							answer.setId(answerId);
							answer.setAnswerDesc(answerDesc);
							if (answers.length > 2) {
								answer.setAnswerImage(answers[2].substring(1));
							}
							answerList.add(answer);
						}
					}
					q.setAnswerList(answerList);
				}
				
				questionMap.get(q.getContentType()).add(q);
			}
			
			logger.info("根据岗位，查看出题规则，岗位类型：" + jobId);
			RuleExample ruleExample = new RuleExample();
			RuleExample.Criteria ruleCriteria = ruleExample.createCriteria();
			if(StringUtils.isNotBlank(jobId)){
				ruleCriteria.andJobIdEqualTo(jobId);
			}
			List<Rule> ruleList = ruleService.selectByExample(ruleExample);
			for (int i = 0; i < ruleList.size(); i++ ) {
				Rule rule = ruleList.get(i);
				int count = Integer.parseInt(rule.getContentRate());
				int oneChoose = (int) (count * (Double.valueOf(rule.getOneChoose()).doubleValue() / 100));
				int manyChoose = (int) (count * (Double.valueOf(rule.getManyChoose()).doubleValue() / 100));
				int judge = (int) (count * (Double.valueOf(rule.getJudge()).doubleValue() / 100));
				
				List<QuestionVo> contentTypeDatas = questionMap.get(rule.getContentType());
				questionList1.addAll(findQuestion(contentTypeDatas, oneChoose,"1"));
				questionList2.addAll(findQuestion(contentTypeDatas, manyChoose,"2"));
				questionList3.addAll(findQuestion(contentTypeDatas, judge,"3"));
			}
			
			model.put("questionList1", questionList1);// 单选
			model.put("questionList2", questionList2);// 多选
			model.put("questionList3", questionList3);// 判断
			
			int order = 0;
			String[] abcdefghijk = {"A","B","C","D","E","F","G","H","I","J","K"};
			// 单选
			String questions = "";
			for (QuestionVo q : questionList1) {
				List<Answer> answers = q.getAnswerList();
				for (int i = 0; i < answers.size(); i++) {
					answers.get(i).setTmp(abcdefghijk[i]);
				}
				q.setOrder(++order);
				questions += ","+q.getId();
			}
			// 多选
			for (QuestionVo q : questionList2) {
				List<Answer> answers = q.getAnswerList();
				for (int i = 0; i < answers.size(); i++) {
					answers.get(i).setTmp(abcdefghijk[i]);
				}
				q.setOrder(++order);
				questions += ","+q.getId();
			}
			// 判断
			for (QuestionVo q : questionList3) {
				q.setOrder(++order);
				questions += ","+q.getId();
			}
			
			String questionId = "";
			// 单选
			for (int i = 0; i < questionList1.size(); i++) {
				questionId += questionList1.get(i).getId() + ",";
			}
			// 多选
			for (int j = 0; j < questionList2.size(); j++) {
				questionId += questionList2.get(j).getId() + ",";
			}
			// 判断
			for (int k = 0; k < questionList3.size(); k++) {
				questionId += questionList3.get(k).getId() + ",";
			}
			
			// 入用户题目表
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
			// 考试时间：45分钟
			String time = "";
			time = DateUtil.formatDate(DateUtil.addMinutesToDate(date, 45));
			logger.info("考试结束时间：" + time);
			model.put("time", time);
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
		
	

