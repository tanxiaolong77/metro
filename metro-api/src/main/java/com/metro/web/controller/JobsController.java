package com.metro.web.controller;

import java.util.Arrays;
import java.util.List;

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
import com.metro.service.JobsService;
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
		model.put("jobs", jobs);
		return "views/sys-jobs.html";
	}
	
	/**
	 * 提交岗位维护数据
	 * 
	 */
	@RequestMapping(value = "jobSet.m", method = RequestMethod.POST)
	public @ResponseBody DataTransObj jobSet(String[] jobIds) {
		try {
			List<String> jobs = null;
			if(jobIds != null){
				jobs = Arrays.asList(jobIds);
			}
			jobsService.jobSet(jobs);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return DataTransObj.onFailure(null,"更新失败");
		}
		return DataTransObj.onSuccess(null,"更新成功");
	}

}
