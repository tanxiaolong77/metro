package com.metro.query;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.quanjing.util.framework.BaseQuery;


public class UserQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** username */
	private java.lang.String username;
	/** password */
	private java.lang.String password;
	/** phone */
	private java.lang.String phone;
	/** name */
	private java.lang.String name;
	/** 0:未知  1：男 2：女 */
	private java.lang.Integer gender;
	/** 1：职场 2：学生 */
	private java.lang.Integer type;
	/** schoolId */
	private java.lang.Long schoolId;
	/** schoolName */
	private java.lang.String schoolName;
	/** 专业 */
	private java.lang.String subject;
	/** company */
	private java.lang.String company;
	/** position */
	private java.lang.String position;
	/** birthday */
	private java.util.Date birthdayBegin;
	private java.util.Date birthdayEnd;
	/** avatar */
	private java.lang.String avatar;
	/** star */
	private Long star;
	/** createTime */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	
	private java.lang.String industry;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.Integer getGender() {
		return this.gender;
	}
	
	public void setGender(java.lang.Integer value) {
		this.gender = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Long getSchoolId() {
		return this.schoolId;
	}
	
	public void setSchoolId(java.lang.Long value) {
		this.schoolId = value;
	}
	
	public java.lang.String getSchoolName() {
		return this.schoolName;
	}
	
	public void setSchoolName(java.lang.String value) {
		this.schoolName = value;
	}
	
	public java.lang.String getSubject() {
		return this.subject;
	}
	
	public void setSubject(java.lang.String value) {
		this.subject = value;
	}
	
	public java.lang.String getCompany() {
		return this.company;
	}
	
	public void setCompany(java.lang.String value) {
		this.company = value;
	}
	
	public java.lang.String getPosition() {
		return this.position;
	}
	
	public void setPosition(java.lang.String value) {
		this.position = value;
	}
	
	public java.util.Date getBirthdayBegin() {
		return this.birthdayBegin;
	}
	
	public void setBirthdayBegin(java.util.Date value) {
		this.birthdayBegin = value;
	}	
	
	public java.util.Date getBirthdayEnd() {
		return this.birthdayEnd;
	}
	
	public void setBirthdayEnd(java.util.Date value) {
		this.birthdayEnd = value;
	}
	
	public java.lang.String getAvatar() {
		return this.avatar;
	}
	
	public void setAvatar(java.lang.String value) {
		this.avatar = value;
	}
	
	public Long getStar() {
		return this.star;
	}
	
	public void setStar(Long value) {
		this.star = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(java.util.Date value) {
		this.createTimeEnd = value;
	}
	

	public java.lang.String getIndustry() {
		return industry;
	}

	public void setIndustry(java.lang.String industry) {
		this.industry = industry;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

