package com.metro.model;


public class SysUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1293554673747148052L;
	
	private java.lang.Long id;
	
	private java.lang.String username;
	
	private java.lang.String nickname;

	private java.lang.String password; 
    
	private java.lang.String userType;
	
	private java.util.Date createTime; 
	
	private java.lang.Integer status;
	

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.String getUsername() {
		return username;
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	public java.lang.String getNickname() {
		return nickname;
	}

	public void setNickname(java.lang.String nickname) {
		this.nickname = nickname;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getUserType() {
		return userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	} 
}
