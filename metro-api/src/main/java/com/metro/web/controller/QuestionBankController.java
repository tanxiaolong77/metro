package com.metro.web.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.metro.request.QuestionUploadRequest;
import com.metro.service.impl.QuestionExcelServiceImpl;
import com.metro.util.DateUtil;
import com.metro.util.ResourceUtil;
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
			@RequestParam("skillType") String skillType,
			@RequestParam("jobsId") String jobsId,
			@RequestParam("jobsName") String jobsName,
			@RequestParam("authLevel") String authLevel) {
		
        String fileName = DateUtil.dateToStr(new Date(), DateUtil.DATE_FORMAT_ALL) + "_" + file.getOriginalFilename();
        String path = ResourceUtil.getPropertyValue("questionBankPath")+fileName;
        File newFile=new File(path);
        try {
        	file.transferTo(newFile);
        	QuestionUploadRequest bizRequest = new QuestionUploadRequest();
        	bizRequest.setPicturePath(ResourceUtil.getPropertyValue("picturePath"));
        	bizRequest.setSkillType(skillType);
        	bizRequest.setJobsId(jobsId);
        	bizRequest.setAuthLevel(authLevel);
			return questionExcelService.parse(newFile,bizRequest);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        return DataTransObj.onFailure(null,"上传失败");
	}
	
}
