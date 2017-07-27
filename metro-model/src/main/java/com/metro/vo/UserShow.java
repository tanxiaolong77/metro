package com.metro.vo;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserShow {

	private Long id;
	private String name;
	private Integer gender;
	private String userName;
	private String orgName;//学校名称或者行业
	private String avatar;
	private Integer type;
	private Long schoolId;
	private String schoolName;
	private String subject;
	private String industry;
	private String company;
	private String position;
	private Date birthday;
	private Double distance;
	private Integer age;
	/***
	 * 身份认证状态
	 *  0：待审核 1：审核成功  2：未审核	-1：审核失败
	 */
	private Integer identityCertStatus;//身份认证状态
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public Integer getAge() {
		return age;
	}
	public void setBirthday(Date birthday) {
		int year = birthday.getYear();
		int year2 = new Date().getYear();
		this.age=year2-year;
		this.birthday = birthday;
	}
	
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("Id",getId())
				.append("Name",getName())
				.append("Gender",getGender())
				.append("UserName",getUserName())
				.append("OrgName",getOrgName())
				.append("Avatar",getAvatar())
				.append("Type",getType())
				.append("SchoolId",getSchoolId())
				.append("SchoolName",getSchoolName())
				.append("Subject",getSubject())
				.append("Industry",getIndustry())
				.append("Company",getCompany())
				.append("Position",getPosition())
				.append("Birthday",getBirthday())
				.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserShow other = (UserShow) obj;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	public Integer getIdentityCertStatus() {
		return identityCertStatus;
	}
	public void setIdentityCertStatus(Integer identityCertStatus) {
		this.identityCertStatus = identityCertStatus;
	}
}
