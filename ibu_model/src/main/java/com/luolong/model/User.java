package com.luolong.model;

import java.io.Serializable;
import java.util.List;

/**
 * 用户类
 * @author 罗龙
 *
 */
public class User extends BaseVo implements Serializable {
	private static final long serialVersionUID = 1173417721814527464L;
	private Integer userId;
	private String userName;
	private String userPassword;
	private String nickName;
	private String phone;
	private String createDate;
	private String type;
	private String inputRand;
	private String roleArr;
	private List<Menu> menuList;
	private List<Permission> permiList;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoleArr() {
		return roleArr;
	}

	public void setRoleArr(String roleArr) {
		this.roleArr = roleArr;
	}

	public List<Permission> getPermiList() {
		return permiList;
	}

	public void setPermiList(List<Permission> permiList) {
		this.permiList = permiList;
	}

	public String getInputRand() {
		return inputRand;
	}

	public void setInputRand(String inputRand) {
		this.inputRand = inputRand;
	}

	
	
	
	

}