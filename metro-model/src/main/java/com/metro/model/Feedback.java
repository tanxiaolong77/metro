/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.metro.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author xiaolong.tan
 * @version 1.0
 * @since 1.0
 */


public class Feedback implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** id **/
	private java.lang.Long id;
	/** 反馈内容 **/
	private java.lang.String feedback;
	/** 来源类型 **/
	private java.lang.Integer sourceType;
	/** 来源id **/
	private java.lang.Long sourceId;
	/** 0：待确认，1：已确认，-1：作废 **/
	private java.lang.Integer status;
	/** createTime **/
	private java.util.Date createTime;
	/** createUser **/
	private java.lang.Long createUser;
	//columns END

	public Feedback(){
	}

	public Feedback(
		java.lang.Long id
	){
		this.id = id;
	}


	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}

	public void setFeedback(java.lang.String value) {
		this.feedback = value;
	}
	
	public java.lang.String getFeedback() {
		return this.feedback;
	}

	public void setSourceType(java.lang.Integer value) {
		this.sourceType = value;
	}
	
	public java.lang.Integer getSourceType() {
		return this.sourceType;
	}

	public void setSourceId(java.lang.Long value) {
		this.sourceId = value;
	}
	
	public java.lang.Long getSourceId() {
		return this.sourceId;
	}

	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Feedback",getFeedback())
			.append("SourceType",getSourceType())
			.append("SourceId",getSourceId())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Feedback == false) return false;
		if(this == obj) return true;
		Feedback other = (Feedback)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public java.lang.Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(java.lang.Long createUser) {
		this.createUser = createUser;
	}
}

