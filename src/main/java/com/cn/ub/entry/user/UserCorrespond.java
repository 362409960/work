package com.cn.ub.entry.user;

import java.io.Serializable;

public class UserCorrespond implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String userUId;
	
	private String userVId;
	
	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	

}
