package com.cn.ub.entry.user;

import java.io.Serializable;

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

	// 主键 uuid生成
	private String id;
	// 用户名称
	private String username;
	// 密码
	private String password;

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

}
