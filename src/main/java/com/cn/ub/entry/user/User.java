package com.cn.ub.entry.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体记录
 * 
 * @author lichuan
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 主键 uuid生成
	 */
	private String id;
	
	/**
	 * 用户名称
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 0超级管理员，1是上次用户，2是查看用户
	 */
	private String userType;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	
}
