package com.metro.web.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.metro.model.Question;
import com.metro.model.Rule;
import com.metro.model.RuleExample;
import com.metro.service.QuestionService;
import com.metro.service.RuleService;
import com.metro.vo.QuestionVo;

/***
 * 考题控制
 * @author dell
 *
 */
@Controller
@RequestMapping(value="question")
public class QuestionController  extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private RuleService ruleService;

	/***
	 * 出题目
	 * @param request
	 * @param response
	 * @param skillType
	 * @param jobId
	 * @param matchLevel
	 */
	@RequestMapping(value = "doTest.u", method = RequestMethod.POST)
	public String doTest(ModelMap model,
			@RequestParam("skillType") String skillType,
			@RequestParam("jobId") String jobId,
			@RequestParam("testType") String testType,
			@RequestParam("matchLevel") String matchLevel) {
		
		// 是否登录之后跳进来的，没资格返回登录页
		//if xxx return error();
		// 倒计时45分钟
		
		
		QuestionVo question = new QuestionVo();
		
		if(StringUtils.isNotBlank(skillType)){
			question.setSkillType(skillType);
		}
		if(StringUtils.isNotBlank(jobId)){
			question.setJobsId(jobId);
		}
		if(StringUtils.isNotBlank(testType)){
			question.setTestType(testType);
		}
		if(StringUtils.isNotBlank(matchLevel)){
			question.setMatchLevel(matchLevel);
		}
		
		List<Question>  questionList1 = new ArrayList<Question>();
		List<Question>  questionList2 = new ArrayList<Question>();
		List<Question>  questionList3 = new ArrayList<Question>();
		// 根据岗位，查看题型占比
		RuleExample ruleExample = new RuleExample();
		RuleExample.Criteria ruleCriteria = ruleExample.createCriteria();
		if(StringUtils.isNotBlank(jobId)){
			ruleCriteria.andSkillTypeEqualTo(jobId);
		}
		List<Rule> ruleList = ruleService.selectByExample(ruleExample);
		for (int i = 0; i < ruleList.size(); i++) {
			Rule rule = ruleList.get(i);
			String contentType = rule.getContentType();
			question.setContentType(contentType);
			// 每一个知识点题量
			int count = 100 * (Integer.parseInt(rule.getContentRate()) / 100);
			// 单选题量
			int oneChoose = count * (Integer.parseInt(rule.getOneChoose()) / 100);
			// 多选题量
			int manyChoose = count * Integer.parseInt(rule.getManyChoose()) / 100;
			// 判断题量
			int judge = count * Integer.parseInt(rule.getJudge()) / 100;
			// 单选题
			question.setQuestionType("1");
			question.setCount(oneChoose);
			questionList1.addAll(questionService.selectByQuestionVo(question));
			// 多选题
			question.setQuestionType("2");
			question.setCount(manyChoose);
			questionList2.addAll(questionService.selectByQuestionVo(question));
			// 判断题
			question.setQuestionType("3");
			question.setCount(judge);
			// 根据技能类型、考试类型、岗位类型、大赛级别、题型数量出题
			questionList3.addAll(questionService.selectByQuestionVo(question));
		}
		
		Collections.shuffle(questionList1);
		Collections.shuffle(questionList2);
		Collections.shuffle(questionList3);
		
		model.put("questionList1", questionList1);// 单选
		model.put("questionList2", questionList2);// 单选
		model.put("questionList3", questionList3);// 单选
		return "question.html";
		
	}
	
}
