package com.metro.web.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.metro.model.Jobs;
import com.metro.model.JobsExample;
import com.metro.model.Rule;
import com.metro.model.RuleExample;
import com.metro.service.JobsService;
import com.metro.service.RuleService;
import com.metro.util.BeanUtils;
import com.metro.util.SessionUtils;
import com.metro.vo.DataTransObj;
import com.metro.vo.RuleVO;

/***
 * 岗位控制
 * 
 * @author dell
 *
 */
@Controller
@RequestMapping(value = "rule")
public class RuleController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(RuleController.class);

	@Autowired
	private JobsService jobsService;

	@Autowired
	private RuleService ruleService;

	/**
	 * 出题规则列表
	 * 
	 */
	@RequestMapping(value = "rulelist.m", method = RequestMethod.GET)
	public String rulelist(ModelMap model) {
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);// 岗位
		return "views/sys-rule-list.html";
	}

	/**
	 * 出题规则列表数据
	 * 
	 */
	@RequestMapping(value = "ruleSearch.m", method = RequestMethod.GET)
	public @ResponseBody DataTransObj ruleSearch(String contentType, String jobsId, Integer startNumber,
			Integer pageSize) {

		RuleExample example = new RuleExample();
		RuleExample.Criteria c = example.createCriteria();

		if (StringUtils.isNotBlank(contentType)) {
			c.andContentTypeLike("%"+contentType+"%");
		}

		if (StringUtils.isNotBlank(jobsId) && !"#".equals(jobsId)) {
			c.andJobIdEqualTo(jobsId);
		}

		int totalNum = ruleService.countByExample(example);

		// 分页
		if(startNumber != null){
			example.setStartNumber(startNumber);
		}
		example.setOrderByClause("create_time desc");
		
		List<Rule> list = ruleService.selectByExample(example);
		List<RuleVO> result = BeanUtils.transfersB(list, RuleVO.class);
		for (RuleVO vo : result) {
			vo.setJobsName(jobsService.getById(vo.getJobId()).getJobsName());
		}

		return DataTransObj.onSuccess(result, "查询成功", totalNum);
	}

	/**
	 * 出题规则新建页面
	 * 
	 */
	@RequestMapping(value = "ruleSetInit.m", method = RequestMethod.GET)
	public String ruleSetInit(ModelMap model) {
		List<Jobs> jobs = jobsService.selectByExample(new JobsExample());
		model.put("jobs", jobs);// 岗位
		return "views/sys-rule-add.html";
	}

	/**
	 * 新建出题规则
	 * 
	 */
	@RequestMapping(value = "ruleSet.m", method = RequestMethod.POST)
	public @ResponseBody DataTransObj ruleSet(
			@RequestParam(value = "jobsId", required = true) String jobsId,
			@RequestParam(value = "rules", required = true) String rules) {



		// if("2".equals(operationType)){
		// //修改则把之前的规则删除
		// RuleExample example = new RuleExample();
		// RuleExample.Criteria c = example.createCriteria();
		// c.andSkillTypeEqualTo(skillType);
		// c.andJobIdEqualTo(jobsId);
		// ruleService.deleteByExample(example);
		// }

		try {
			// 新增
			ruleService.add(Arrays.asList(rules.split(",")), jobsId, SessionUtils.getLoginManager().getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return DataTransObj.onFailure(null, "操作失败");
		}

	

		return DataTransObj.onSuccess(null, "操作成功");
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "ruleDel.m", method = RequestMethod.GET)
	public @ResponseBody DataTransObj ruleDel(ModelMap model, @RequestParam(value = "id", required = true) String id) {

		ruleService.deleteById(id);
		return DataTransObj.onSuccess(null, "操作成功");
	}

}
