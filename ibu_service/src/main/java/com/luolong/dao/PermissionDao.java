package com.luolong.dao;

import java.util.List;

import com.luolong.model.Permission;
import com.luolong.model.User;

public interface PermissionDao {
	public List<Permission> queryAllPermission();
	
	List<Permission> queryUserPermission(User user);
	
}
