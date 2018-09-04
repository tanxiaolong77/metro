package com.metro.vo;

public class ScoreVO {
    private String id;

    private String userId;

    private String realName;

    private String userName;

    private String userCard;

    private String userNumber;

    private String theoryScore = "0";

    private String operateScore = "0";

    private String addupScore = "0";

    private String isPass;

    private String skillType;

    private String testType;

    private String jobsId;

    private String jobsName;

    private String matchLevel;

    private String authLevel;

    private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getTheoryScore() {
		return theoryScore;
	}

	public void setTheoryScore(String theoryScore) {
		this.theoryScore = theoryScore;
	}

	public String getOperateScore() {
		return operateScore;
	}

	public void setOperateScore(String operateScore) {
		this.operateScore = operateScore;
	}

	public String getAddupScore() {
		return addupScore;
	}

	public void setAddupScore(String addupScore) {
		this.addupScore = addupScore;
	}

	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}

	public String getSkillType() {
		return "1".equals(skillType) ? "技能大赛" : "技能鉴定";
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	public String getTestType() {
		return "1".equals(testType) ? "模拟" : "考试";
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getJobsId() {
		return jobsId;
	}

	public void setJobsId(String jobsId) {
		this.jobsId = jobsId;
	}

	public String getJobsName() {
		return jobsName;
	}

	public void setJobsName(String jobsName) {
		this.jobsName = jobsName;
	}

	public String getMatchLevel() {
		return matchLevel;
	}

	public void setMatchLevel(String matchLevel) {
		this.matchLevel = matchLevel;
	}

	public String getAuthLevel() {
		return authLevel;
	}

	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}