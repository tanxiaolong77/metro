package com.metro.request;

public class ScoreRequest implements Request {

	private String realName;
	private String userCard;
	private String jobsId;
	private String skillType;
	private String testType;
	private String theoryScoreGt;
	private String theoryScoreLt;
	private String operateScoreGt;
	private String operateScoreLt;
	private String addupScoreGt;
	private String addupScoreLt;
	private String startDate;
	private String enDate;
	private Integer startNumber;
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserCard() {
		return userCard;
	}
	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}
	public String getJobsId() {
		return jobsId;
	}
	public void setJobsId(String jobsId) {
		this.jobsId = jobsId;
	}
	public String getSkillType() {
		return skillType;
	}
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTheoryScoreGt() {
		return theoryScoreGt;
	}
	public void setTheoryScoreGt(String theoryScoreGt) {
		this.theoryScoreGt = theoryScoreGt;
	}
	public String getTheoryScoreLt() {
		return theoryScoreLt;
	}
	public void setTheoryScoreLt(String theoryScoreLt) {
		this.theoryScoreLt = theoryScoreLt;
	}
	public String getOperateScoreGt() {
		return operateScoreGt;
	}
	public void setOperateScoreGt(String operateScoreGt) {
		this.operateScoreGt = operateScoreGt;
	}
	public String getOperateScoreLt() {
		return operateScoreLt;
	}
	public void setOperateScoreLt(String operateScoreLt) {
		this.operateScoreLt = operateScoreLt;
	}
	public String getAddupScoreGt() {
		return addupScoreGt;
	}
	public void setAddupScoreGt(String addupScoreGt) {
		this.addupScoreGt = addupScoreGt;
	}
	public String getAddupScoreLt() {
		return addupScoreLt;
	}
	public void setAddupScoreLt(String addupScoreLt) {
		this.addupScoreLt = addupScoreLt;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEnDate() {
		return enDate;
	}
	public void setEnDate(String enDate) {
		this.enDate = enDate;
	}
	public Integer getStartNumber() {
		return startNumber;
	}
	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}
}
