package com.metro.web.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.metro.model.Answer;
import com.metro.model.AnswerExample;
import com.metro.model.Jobs;
import com.metro.model.JobsExample;
import com.metro.model.Question;
import com.metro.model.QuestionExample;
import com.metro.request.QuestionUploadRequest;
import com.metro.service.AnswerService;
import com.metro.service.JobsService;
import com.metro.service.QuestionService;
import com.metro.service.impl.QuestionExcelServiceImpl;
import com.metro.util.BeanUtils;
import com.metro.util.DateUtil;
import com.metro.util.ResourceUtil;
import com.metro.util.SessionUtils;
import com.metro.vo.DataTransObj;
import com.metro.vo.QuestionShow;

/***
 * 题库控制
 * @author dell
 *
 */
@Controller
@RequestMapping(value="qabank")
public class QuestionBankController  extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(QuestionBankController.class);

	@Autowired
	private QuestionExcelServiceImpl questionExcelService;
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	/**
	 * 出题规则列表
	 * 
	 */
	@RequestMapping(value = "qalist.m", method = RequestMethod.GET)
	public String rulelist(ModelMap model) {
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);// 岗位
		return "views/sys-questionbank-list.html";
	}

	/**
	 * 出题规则列表数据
	 * 
	 */
	@RequestMapping(value = "qaSearch.m", method = RequestMethod.GET)
	public @ResponseBody DataTransObj ruleSearch(
			String questionDesc,
			String contentType, 
			String jobsId, 
			Integer startNumber,
			Integer pageSize) {

		QuestionExample example = new QuestionExample();
		QuestionExample.Criteria c = example.createCriteria();


		if (StringUtils.isNotBlank(jobsId) && !"#".equals(jobsId)) {
			c.andJobsIdEqualTo(jobsId);
		}
		
		if (StringUtils.isNotBlank(contentType)) {
			c.andContentTypeLike("%"+contentType+"%");
		}
		
		if (StringUtils.isNotBlank(questionDesc)) {
			c.andQuestionDescLike("%"+questionDesc+"%");
		}
		
		

		int totalNum = questionService.countByExample(example);

		// 分页
		if(startNumber != null){
			example.setStartNumber(startNumber);
		}
		example.setOrderByClause("create_time desc");
		
		List<Question> list = questionService.selectByExample(example);
		List<QuestionShow> result = BeanUtils.transfersB(list, QuestionShow.class);
		for (QuestionShow vo : result) {
			vo.setJobsName(jobsService.getById(vo.getJobsId()).getJobsName());
			if(vo.getAnswerId().length() == 1){
				//判断题
				vo.setAnswerDesc("0".equals(vo.getAnswerId()) ? "正确" : "错误");
			}else{
				//单选或者多选
				String[] answerIdArray = vo.getAnswerId().split(",");
				AnswerExample answerExample = new AnswerExample();
				answerExample.createCriteria().andQuestionIdEqualTo(vo.getId());
				answerExample.setOrderByClause("sort_id asc");
				List<Answer> answers = answerService.selectByExample(answerExample);
				if(answers != null && answers.size() == 4){
					vo.setA(answers.get(0).getAnswerDesc());
					answers.get(0).setTmp("A");
					vo.setB(answers.get(1).getAnswerDesc());
					answers.get(1).setTmp("B");
					vo.setC(answers.get(2).getAnswerDesc());
					answers.get(2).setTmp("C");
					vo.setD(answers.get(3).getAnswerDesc());
					answers.get(3).setTmp("D");
					//查找正确答案
					for (int i = 0; i < answerIdArray.length; i++) {
						for (int j = 0; j < answers.size(); j++) {
							if(answerIdArray[i].equals(answers.get(j).getId())){
								String answerDescStr = StringUtils.isBlank(vo.getAnswerDesc()) ? "" : vo.getAnswerDesc();
								vo.setAnswerDesc(answerDescStr + answers.get(j).getTmp());//多选时答案累加
								break;
							}
						}
					}
				}
			}
		}

		return DataTransObj.onSuccess(result, "查询成功", totalNum);
	}
	
	
	/**
	 * 题目上传
	 * 
	 */
	@RequestMapping(value = "questionUploadInt.m", method = RequestMethod.GET)
	public String questionUploadInt(ModelMap model) {
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);//岗位
		return "views/sys-question-upload.html";
	}
	
	/***
	 * 题库上传
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/upload.m")
	public String upload(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile file,
			@RequestParam("jobsId") String jobsId,
			@RequestParam("jobsName") String jobsName) {
		
        String fileName = DateUtil.dateToStr(new Date(), DateUtil.DATE_FORMAT_ALL) + "_" + file.getOriginalFilename();
        String path = ResourceUtil.getPropertyValue("questionBankPath");
        File dir = new File(path);
    	if(!dir.exists()){
    		dir.mkdirs();
    	}
        File newFile = new File(path+fileName);
        ModelMap model = new ModelMap();
        try {
        	file.transferTo(newFile);
        	QuestionUploadRequest bizRequest = new QuestionUploadRequest();
        	bizRequest.setPicturePath(ResourceUtil.getPropertyValue("picturePath"));
        	bizRequest.setJobsId(jobsId);
        	bizRequest.setJobsName(jobsName);
        	bizRequest.setUserId(SessionUtils.getLoginManager().getId());
        	questionExcelService.parse(newFile,bizRequest);
        	model.put("result","上传成功");
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			model.put("result","上传失败");
		}
        return questionUploadInt(model);
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "qaDel.m", method = RequestMethod.GET)
	public @ResponseBody DataTransObj matchDel(ModelMap model,
			@RequestParam(value="id", required = true) String id) {
		questionService.deleteById(id);
		AnswerExample answerExample = new AnswerExample();
		answerExample.createCriteria().andQuestionIdEqualTo(id);
		answerService.deleteByExample(answerExample);
		return DataTransObj.onSuccess(null,"操作成功");
	}
	
}
