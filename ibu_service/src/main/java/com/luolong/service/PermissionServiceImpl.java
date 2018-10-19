package com.luolong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luolong.dao.PermissionDao;
import com.luolong.model.Permission;
import com.luolong.model.User;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public List<Permission> queryAllPermission() {
		return permissionDao.queryAllPermission();
	}
	
	@Override
	public List<Permission> queryUserPermission(User user) {
		List<Permission> permiList = null;
		if(user.getUserId()==1){
			permiList = permissionDao.queryAllPermission();
		}else{
			permiList = permissionDao.queryUserPermission(user);
		}
		return permiList;
	}

}
