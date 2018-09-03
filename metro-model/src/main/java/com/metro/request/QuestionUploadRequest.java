package com.metro.request;

/***
 * 题库上传
 * @author dell
 *
 */
public class QuestionUploadRequest implements Request  {
	
	private String userId;
	
	private String 	picturePath;
	
	private String skillType;
	
	private String jobsId;
	
	private String jobsName;
	
	private String authLevel;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getSkillType() {
		return skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	public String getJobsId() {
		return jobsId;
	}

	public void setJobsId(String jobsId) {
		this.jobsId = jobsId;
	}

	public String getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}

	public String getJobsName() {
		return jobsName;
	}

	public void setJobsName(String jobsName) {
		this.jobsName = jobsName;
	}
}
