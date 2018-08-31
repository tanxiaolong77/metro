package com.metro.web.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.metro.request.QuestionUploadRequest;
import com.metro.util.DateUtil;
import com.metro.util.QuestionExcelParser;
import com.metro.util.ResourceUtil;

/***
 * 题库控制
 * @author dell
 *
 */
@Controller
@RequestMapping(value="qabank")
public class QuestionBankController  extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(QuestionBankController.class);

	/***
	 * 题库上传
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/upload.m")
	public void upload(HttpServletRequest request,HttpServletResponse response,
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
        	bizRequest.setSkillType(skillType);
        	bizRequest.setJobsId(jobsId);
        	bizRequest.setAuthLevel(authLevel);
			QuestionExcelParser.parse(newFile,bizRequest);
			response.getWriter().write("上传成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
}
