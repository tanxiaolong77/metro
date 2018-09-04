package com.metro.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metro.model.Employee;
import com.metro.model.Question;
import com.metro.model.QuestionExample;
import com.metro.model.Score;
import com.metro.service.QuestionService;
import com.metro.service.ScoreService;
import com.metro.util.SessionUtils;
import com.metro.vo.DataTransObj;

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
	@RequestMapping(value = "userAnswer", method = RequestMethod.POST)
	public @ResponseBody DataTransObj userAnswer(
			@RequestParam(value="userId",required=true) String userId,
			@RequestParam(value="questionId",required=true) String questionId,
			@RequestParam(value="questionType",required=true) String questionType,
			@RequestParam(value="answerId",required=true) String answerId,
			HttpServletRequest request) {
			logger.info("进入评分后台！！！");
			
			// 最终得分
			double userScore = 0;
			
			// 答题有效
			if (StringUtils.isNoneBlank(userId)
					&& StringUtils.isNoneBlank(questionId)
					&& StringUtils.isNoneBlank(questionType)
					&& StringUtils.isNoneBlank(answerId)) {
				logger.info("答题有效，开始计算分数");
			
				// 计算分数
				// 单选题答对得1分
				if ("1".equals(questionType)) {
					QuestionExample questionExample = new QuestionExample();
					QuestionExample.Criteria questionCriteria = questionExample.createCriteria();
					questionCriteria.andIdEqualTo(questionId);
					List<Question> questionList = questionService.selectByExample(questionExample);
					Question question = questionList.get(0);
					String questionAnswerId = question.getAnswerId();
					if (answerId.equals(questionAnswerId)) {
						userScore = userScore + 1;
					}
					logger.info("单选得分：" + userScore + "分");
				}
				
				// 多选题答对得1.5分
				if ("2".equals(questionType)) {
					QuestionExample questionExample = new QuestionExample();
					QuestionExample.Criteria questionCriteria = questionExample.createCriteria();
					questionCriteria.andIdEqualTo(questionId);
					List<Question> questionList = questionService.selectByExample(questionExample);
					Question question = questionList.get(0);
					String questionAnswerId = question.getAnswerId();
					if (answerId.equals(questionAnswerId)) {
						userScore = userScore + 1.5;
					}
					logger.info("多选得分：" + userScore + "分");
				}
				
				// 判断题答对得0.5分
				if ("3".equals(questionType)) {
					if ("1".equals(answerId)) {
						userScore = userScore + 0.5;
					}
					logger.info("判断得分：" + userScore + "分");
				}
				
				// 入分数表
				Score score = new Score();
				Employee user = SessionUtils.getLoginUser();
				String skillType = SessionUtils.getSkillType();
				
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
				score.setSkillType(skillType);
				if ("1".equals(skillType)) {
					score.setMatchLevel(SessionUtils.getMatchLevel());
				} else {
					score.setAuthLevel(SessionUtils.getAuthLevel());
				}
				score.setTestType(SessionUtils.getTestType());
//				score.setJobsType(SessionUtils.getJobType());
				score.setCreateTime(new Date());
				scoreService.insert(score);
				
				return DataTransObj.onSuccess(null, "本次答题得分：" + userScore + "分");
			
			// 答题无效	
			} else {
				return DataTransObj.onSuccess(null, "本次答题得分：" + userScore + "分");
			}
			
		
	}

}
