package com.metro.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metro.common.constant.Constant;
import com.metro.model.Employee;
import com.metro.model.Question;
import com.metro.model.QuestionExample;
import com.metro.model.Score;
import com.metro.service.QuestionService;
import com.metro.service.ScoreService;
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
	
	/**
	 * 用户提交答案
	 * @return
	 */
	@RequestMapping(value = "getScore.u", method = RequestMethod.POST)
	public @ResponseBody DataTransObj getScore(String answers, HttpServletRequest request) {
			logger.info("进入评分后台！！！");
			
			double userScore = 0;// 得分
			
			String questionId = "";// 用户答案的题目id
			String answerId = "";// 用户答案的答案id
			
			ArrayList<String> questionIdList = new ArrayList<String>();// 存放用户答案的题目id，用于查看正确答案
			List<QuestionVo> userQuestionAnswerList = new ArrayList<QuestionVo>();// 存放用户答案的答案id，用于计算得分
			
			answers = StringUtils.trim(answers);
			String[] answersObj = answers.split(",");
			// 答题有效
			if (answersObj != null && answersObj.length > 0) {
				logger.info("答题有效，开始计算分数");
				for (int i = 0; i < answersObj.length; i++) {
					String[] questionIdAndAnswerId = answersObj[i].split("#");
					questionId = questionIdAndAnswerId[0];
					questionIdList.add(questionId);
					if (questionIdAndAnswerId.length > 2) {
						for (int j = 1; j < questionIdAndAnswerId.length; j++) {
							answerId = questionIdAndAnswerId[j] + ",";
						}
					} else {
						answerId = questionIdAndAnswerId[1];
					}
					
					QuestionVo questionVo = new QuestionVo();
					questionVo.setId(questionId);
					questionVo.setAnswerId(answerId);
					userQuestionAnswerList.add(questionVo);
				}
				
				// 试题信息
				List<QuestionVo> questionVoList = questionService.selectByQuestionId(questionIdList);
				
				// 计算得分
				userScore = calculateScore(questionVoList,userQuestionAnswerList);
				
			// 答题无效	
			} else {
				return DataTransObj.onSuccess(null, "本次答题得分：" + userScore + "分");
			}
			
			// 入分数表
			HttpSession session = request.getSession();
			Employee user = (Employee) session.getAttribute(Constant.SESSION_LOGIN_USER);
			String userId = user.getId();
			
			Score score = new Score();
			score.setUserId(userId);
			score.setUserName(user.getRealName());
			score.setUserCard(user.getUserCard());
			score.setUserNumber(user.getUserNumber());
			score.setTheoryScore(String.valueOf(userScore));
			score.setOperateScore("0");
			score.setAddupScore(String.valueOf(userScore + 0));
			if (userScore > 60) {
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
			score.setCreateTime(new Date());
			scoreService.insert(score);
			
			return DataTransObj.onSuccess(null, "本次答题得分：" + userScore + "分");
		
	}

	private double calculateScore(List<QuestionVo> questionList, List<QuestionVo> questionAnswerIdList) {
		
		// 最终得分
		double userScore = 0;
		// 单选得分
		double oneScore = 0;
		// 多选得分
		double manyScore = 0;
		// 判断得分
		double judgeScore = 0;
		
		for (int i = 0; i < questionList.size(); i++) {
			for (int j = 0; j < questionAnswerIdList.size(); j++) {
				// 单选题答对得1分
				if ("1".equals(questionList.get(i).getQuestionType())) {
					if (questionAnswerIdList.get(j).getId().equals(questionList.get(i).getId()) 
						&& questionAnswerIdList.get(j).getAnswerId().equals(questionList.get(i).getAnswerId())) {
						oneScore = oneScore + 1;
					}
				}
				// 多选题答对得1.5分
				if ("2".equals(questionList.get(i).getQuestionType())) {
					if (questionAnswerIdList.get(j).getId().equals(questionList.get(i).getId()) 
							&& questionAnswerIdList.get(j).getAnswerId().equals(questionList.get(i).getAnswerId())) {
						manyScore = manyScore + 1.5;
					}
				}
				// 判断题答对得0.5分
				if ("3".equals(questionList.get(i).getQuestionType())) {
					if (questionAnswerIdList.get(j).getId().equals(questionList.get(i).getId()) 
							&& questionAnswerIdList.get(j).getAnswerId().equals(questionList.get(i).getAnswerId())) {
						judgeScore = judgeScore + 0.5;
					}
				}
			}
		}
		userScore = oneScore + manyScore + judgeScore;
		
		logger.info("单选得分：" + oneScore + "分");
		logger.info("多选得分：" + manyScore + "分");
		logger.info("判断得分：" + judgeScore + "分");
		logger.info("最终得分：" + userScore + "分");
		
		return userScore;
	}

}
