package com.metro.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.metro.model.Jobs;
import com.metro.model.JobsExample;
import com.metro.request.QuestionUploadRequest;
import com.metro.service.JobsService;
import com.metro.service.impl.QuestionExcelServiceImpl;
import com.metro.util.DateUtil;
import com.metro.util.ResourceUtil;
import com.metro.util.SessionUtils;
import com.metro.vo.DataTransObj;

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
	public DataTransObj upload(HttpServletRequest request,HttpServletResponse response,
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
        try {

        	file.transferTo(newFile);
        	QuestionUploadRequest bizRequest = new QuestionUploadRequest();
        	bizRequest.setPicturePath(ResourceUtil.getPropertyValue("picturePath"));
        	bizRequest.setJobsId(jobsId);
        	bizRequest.setJobsName(jobsName);
        	bizRequest.setUserId(SessionUtils.getLoginManager().getId());
			return questionExcelService.parse(newFile,bizRequest);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return DataTransObj.onFailure(null,"上传失败");
	}
	
}
