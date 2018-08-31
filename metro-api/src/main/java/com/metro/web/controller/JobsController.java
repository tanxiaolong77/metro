package com.metro.web.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.metro.model.Jobs;
import com.metro.model.JobsExample;
import com.metro.request.QuestionUploadRequest;
import com.metro.service.JobsService;
import com.metro.util.DateUtil;
import com.metro.util.QuestionExcelParser;
import com.metro.util.ResourceUtil;
import com.metro.vo.DataTransObj;

/***
 * 岗位控制
 * @author dell
 *
 */
@Controller
@RequestMapping(value="jobs")
public class JobsController  extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(JobsController.class);
	
	@Autowired
	private JobsService jobsService;
	
	
	/**
	 * 跳转到岗位维护页面
	 * 
	 */
	@RequestMapping(value = "jobSetInit.m", method = RequestMethod.GET)
	public String jobSetInit(ModelMap model) {
		
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("result", jobs);
		return "jobs.html";
	}
	
	/**
	 * 提交岗位维护数据
	 * 
	 */
	@RequestMapping(value = "jobSet.m", method = RequestMethod.POST)
	public @ResponseBody DataTransObj jobSetInit(
			@RequestParam(value="jobId", required = true) List<String> jobIds
			) {
		
		//全部更改为不显示
		Jobs job = new Jobs();
		job.setIsShow("1");//不显示
		JobsExample example = new JobsExample();
		jobsService.updateByExample(job,example);
		
		if(jobIds != null && jobIds.size() > 0){
			//将选择显示的部分修改
			job.setIsShow("0");//显示
			JobsExample.Criteria c = example.createCriteria();
			c.andIdIn(jobIds);
			jobsService.updateByExample(job,example);
		}
		return DataTransObj.onSuccess(null,"更新成功");
	}

}
