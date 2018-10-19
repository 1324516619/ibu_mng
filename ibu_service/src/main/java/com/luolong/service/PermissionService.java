package com.luolong.service;

import java.util.List;

import com.luolong.model.Permission;
import com.luolong.model.User;

public interface PermissionService {
	
	
	List<Permission> queryAllPermission();
	
	List<Permission> queryUserPermission(User user);
	

}
