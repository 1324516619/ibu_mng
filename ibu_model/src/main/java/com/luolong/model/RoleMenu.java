package com.luolong.model;

import java.io.Serializable;

/**
 * 角色菜单实体 
 * @author 罗龙
 */
public class RoleMenu implements Serializable {
	private static final long serialVersionUID = 1507623458335688483L;
	private Integer roleMenuId;// 主键
	private Integer roleId;
	private Integer menuId;
	public Integer getRoleMenuId() {
		return roleMenuId;
	}
	public void setRoleMenuId(Integer roleMenuId) {
		this.roleMenuId = roleMenuId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	
	
}
