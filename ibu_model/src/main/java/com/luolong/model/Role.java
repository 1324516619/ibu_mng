package com.luolong.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色实体
 * 
 * @author 罗龙
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1007623458335688483L;
	private int roleId;// 主键
	private String roleName;// 角色名称
	private String creator;// 创建人
	private String menuArr;
	private String permiArr;
	private List<Long> menuIdList = new ArrayList<Long>(); // 菜单id列表
	private List<String> permiList = new ArrayList<String>(); // 权限列表

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getMenuArr() {
		return menuArr;
	}

	public void setMenuArr(String menuArr) {
		this.menuArr = menuArr;
	}

	public String getPermiArr() {
		return permiArr;
	}

	public void setPermiArr(String permiArr) {
		this.permiArr = permiArr;
	}

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public void addMenuId(Long menuId) {
		this.menuIdList.add(menuId);
	}

	public List<String> getPermiList() {
		return permiList;
	}

	public void setPermiList(List<String> permiList) {
		this.permiList = permiList;
	}

	public void addPermission(String permission) {
		this.permiList.add(permission);
	}

}
