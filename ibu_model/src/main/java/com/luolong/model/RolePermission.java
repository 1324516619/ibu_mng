package com.luolong.model;

import java.io.Serializable;

/**
 * 角色权限实体 
 * @author 罗龙
 */
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1507623458335688883L;
	private Integer id;// 主键
	private Integer roleId;
	private String permission;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
	
}
