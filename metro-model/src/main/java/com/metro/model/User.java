package com.metro.model;




import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	
	
	
	
	//columns START
    /**
     * id       db_column: id  
     * 
	@PrimaryKey
	*/	
	 
	private java.lang.Long id;
    /**
     * nickName       db_column: nick_name  
     * 
*/	
	 @Length(max=50)
	private java.lang.String nickName;
    /**
     * username       db_column: username  
     * 
*/	
	 @NotBlank @Length(max=40)
	private java.lang.String username;
    /**
     * password       db_column: password  
     * 
*/	
	 @NotBlank @Length(max=60)
	private java.lang.String password;
    /**
     * phone       db_column: phone  
     * 
*/	
	 @NotBlank @Length(max=20)
	private java.lang.String phone;
    /**
     * name       db_column: name  
     * 
*/	
	 @NotBlank @Length(max=10)
	private java.lang.String name;
    /**
     * 0:未知  1：男 2：女       db_column: gender  
     * 
*/	
	 @NotNull 
	private java.lang.Integer gender;
    /**
     * 1：职场 2：学生       db_column: type  
     * 
*/	
	 
	/***
	 * 男
	 */
	public static final java.lang.Integer 	MALE = 1;
	
	/***
	 * 女
	 */
	public static final java.lang.Integer FEMALE = 2;
		
	 @NotNull 
	private java.lang.Integer type;
    /**
     * schoolId       db_column: school_id  
     * 
*/	
	 
	private java.lang.Long schoolId;
    /**
     * schoolName       db_column: school_name  
     * 
*/	
	 @Length(max=50)
	private java.lang.String schoolName;
    /**
     * 专业       db_column: subject  
     * 
*/	
	 @Length(max=50)
	private java.lang.String subject;
    /**
     * company       db_column: company  
     * 
*/	
	 @Length(max=50)
	private java.lang.String company;
    /**
     * position       db_column: position  
     * 
*/	
	 @Length(max=50)
	private java.lang.String position;
    /**
     * birthday       db_column: birthday  
     * 
*/	
	 @NotNull 
	private java.util.Date birthday;
    /**
     * avatar       db_column: avatar  
     * 
*/	
	 @NotBlank @Length(max=100)
	private java.lang.String avatar;
    /**
     * 情感状态       db_column: emotional  
     * 
*/	
	 @Length(max=20)
	private java.lang.String emotional;
    /**
     * 签名       db_column: signature  
     * 
*/	
	 @Length(max=200)
	private java.lang.String signature;
    /**
     * 家乡城市       db_column: home_town  
     * 
*/	
	 
	private java.lang.Long homeTown;
    /**
     * 兴趣json串       db_column: interest  
     * 
*/	
	 @Length(max=2000)
	private java.lang.String interest;
    /**
     * star       db_column: star  
     * 
*/	
	 
	private java.lang.Float star;
	/**
	 * truth_info_star    db_column:truth_info_star
	 * 信息真实度评分
	 */
	private java.lang.Float truthInfoStar;
	/**
	 * friendly_star     db_column: friendly_star
	 * 友好亲切度评分
	 */
	private java.lang.Float friendlyStar;
	/**
	 * appear_star     db_column:appear_star
	 * 颜值爆表度
	 */
	private java.lang.Float  appearStar;
    /**
     * createTime       db_column: create_time  
     * 
*/	
	 @NotNull 
	private java.util.Date createTime;
    /**
     * status       db_column: status  
     * 
*/	
	 
	private java.lang.Integer status;
    /**
     * lastLoginTime       db_column: last_login_time  
     * 
*/	
	 
	private java.util.Date lastLoginTime;
	//columns END
	
	private java.lang.String industry;
	
	//--------------------------临时属性-------------------------------
	private Long dynamicCount;//用户动态数
	
	private Long activityCount;//用户发布活动数

	public User(){
	}

	public User(
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
		
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}
		
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
		
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
		
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
		
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
		
	public void setGender(java.lang.Integer value) {
		this.gender = value;
	}
	
	public java.lang.Integer getGender() {
		return this.gender;
	}
		
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
	}
		
	public void setSchoolId(java.lang.Long value) {
		this.schoolId = value;
	}
	
	public java.lang.Long getSchoolId() {
		return this.schoolId;
	}
		
	public void setSchoolName(java.lang.String value) {
		this.schoolName = value;
	}
	
	public java.lang.String getSchoolName() {
		return this.schoolName;
	}
		
	public void setSubject(java.lang.String value) {
		this.subject = value;
	}
	
	public java.lang.String getSubject() {
		return this.subject;
	}
		
	public void setCompany(java.lang.String value) {
		this.company = value;
	}
	
	public java.lang.String getCompany() {
		return this.company;
	}
		
	public void setPosition(java.lang.String value) {
		this.position = value;
	}
	
	public java.lang.String getPosition() {
		return this.position;
	}
		
	public void setBirthday(java.util.Date value) {
		this.birthday = value;
	}
	
	public java.util.Date getBirthday() {
		return this.birthday;
	}
		
	public void setAvatar(java.lang.String value) {
		this.avatar = value;
	}
	
	public java.lang.String getAvatar() {
		return this.avatar;
	}
		
	public void setEmotional(java.lang.String value) {
		this.emotional = value;
	}
	
	public java.lang.String getEmotional() {
		return this.emotional;
	}
		
	public void setSignature(java.lang.String value) {
		this.signature = value;
	}
	
	public java.lang.String getSignature() {
		return this.signature;
	}
		
	public void setHomeTown(java.lang.Long value) {
		this.homeTown = value;
	}
	
	public java.lang.Long getHomeTown() {
		return this.homeTown;
	}
		
	public void setInterest(java.lang.String value) {
		this.interest = value;
	}
	
	public java.lang.String getInterest() {
		return this.interest;
	}
		
	public void setStar(Float value) {
		this.star = value;
	}
	
	public Float getStar() {
		return this.star;
	}
		
	public java.lang.Float getTruthInfoStar() {
		return truthInfoStar;
	}

	public void setTruthInfoStar(java.lang.Float truthInfoStar) {
		this.truthInfoStar = truthInfoStar;
	}

	public java.lang.Float getFriendlyStar() {
		return friendlyStar;
	}

	public void setFriendlyStar(java.lang.Float friendlyStar) {
		this.friendlyStar = friendlyStar;
	}

	public java.lang.Float getAppearStar() {
		return appearStar;
	}

	public void setAppearStar(java.lang.Float appeaStar) {
		this.appearStar = appeaStar;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
		
	public void setLastLoginTime(java.util.Date value) {
		this.lastLoginTime = value;
	}
	
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public java.lang.String getIndustry() {
		return industry;
	}

	public void setIndustry(java.lang.String industry) {
		this.industry = industry;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("NickName",getNickName())
			.append("Username",getUsername())
			.append("Password",getPassword())
			.append("Phone",getPhone())
			.append("Name",getName())
			.append("Gender",getGender())
			.append("Type",getType())
			.append("SchoolId",getSchoolId())
			.append("SchoolName",getSchoolName())
			.append("Subject",getSubject())
			.append("Company",getCompany())
			.append("Position",getPosition())
			.append("Birthday",getBirthday())
			.append("Avatar",getAvatar())
			.append("Emotional",getEmotional())
			.append("Signature",getSignature())
			.append("HomeTown",getHomeTown())
			.append("Interest",getInterest())
			.append("Star",getStar())
			.append("TruthInfoStar",getTruthInfoStar())
			.append("FriendlyStar",getFriendlyStar())
			.append("AppearStar",getAppearStar())
			.append("CreateTime",getCreateTime())
			.append("Status",getStatus())
			.append("LastLoginTime",getLastLoginTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public Long getDynamicCount() {
		return dynamicCount;
	}

	public void setDynamicCount(Long dynamicCount) {
		this.dynamicCount = dynamicCount;
	}

	public Long getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(Long activityCount) {
		this.activityCount = activityCount;
	}
}

