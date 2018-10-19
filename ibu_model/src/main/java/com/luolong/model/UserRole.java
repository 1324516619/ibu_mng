package com.luolong.model;

import java.io.Serializable;

/**
 * 用户角色实体
 * @author 罗龙
 */
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1507623458135688483L;
	private Integer accountRoleId;// 主键
	private Integer userId;
	private Integer roleId;
	public Integer getAccountRoleId() {
		return accountRoleId;
	}
	public void setAccountRoleId(Integer accountRoleId) {
		this.accountRoleId = accountRoleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
	
}
