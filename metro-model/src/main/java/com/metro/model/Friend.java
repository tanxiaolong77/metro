package com.metro.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class Friend  implements java.io.Serializable,Cloneable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	
	
	
	
	//columns START
    /**
     * id       db_column: id  
     * 
     * 
     * 
     
	@PrimaryKey
	*/	
	private java.lang.Long id;
    /**
     * userId       db_column: user_id  
     * 
     * 
     * @NotNull 
     
*/	
	private java.lang.Long userId;
    /**
     * friendId       db_column: friend_id  
     * 
     * 
     * @NotNull 
     
*/	
	private java.lang.Long friendId;
    /**
     * createTime       db_column: create_time  
     * 
     * 
     * 
     
*/	
	private java.util.Date createTime;
	//columns END

	public Friend(){
	}

	public Friend(
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
		
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	public java.lang.Long getUserId() {
		return this.userId;
	}
		
	public void setFriendId(java.lang.Long value) {
		this.friendId = value;
	}
	
	public java.lang.Long getFriendId() {
		return this.friendId;
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
			.append("UserId",getUserId())
			.append("FriendId",getFriendId())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Friend == false) return false;
		if(this == obj) return true;
		Friend other = (Friend)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

