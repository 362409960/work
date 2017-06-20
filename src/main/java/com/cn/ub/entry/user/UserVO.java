package com.cn.ub.entry.user;

public class UserVO {
	
	private String id;
	
	private String userUId;
	
	private String userUpName;
	
	
	private String userViewName;
	
	private String userVId;
	
	private String username;
	

	/**
	 * 0超级管理员，1是上次用户，2是查看用户
	 */
	private String userType;
	


	public String getUserUId() {
		return userUId;
	}

	public void setUserUId(String userUId) {
		this.userUId = userUId;
	}

	public String getUserVId() {
		return userVId;
	}

	public void setUserVId(String userVId) {
		this.userVId = userVId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserUpName() {
		return userUpName;
	}

	public void setUserUpName(String userUpName) {
		this.userUpName = userUpName;
	}

	public String getUserViewName() {
		return userViewName;
	}

	public void setUserViewName(String userViewName) {
		this.userViewName = userViewName;
	}

	
	
	

}
