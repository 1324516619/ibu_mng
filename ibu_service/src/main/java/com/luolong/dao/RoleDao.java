package com.luolong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luolong.model.Role;
import com.luolong.model.RoleMenu;
import com.luolong.model.RolePermission;
import com.luolong.util.dialect.Page;

public interface RoleDao {
	
	List<Role> getPage(@Param("page") Page<Role> page,@Param("role") Role role);
	
	int add(Role role);
	
	int addRoleMenu(RoleMenu roleMenu);
	
	int addRolePermission(RolePermission rolePermission);
	
	int edit(Role role);
	
	int delRoleMenu(Role role);
	
	int delRolePermission(Role role);
	
	Role getRoleById(Role role);
	
	List<RoleMenu> getMenuByRole(Role role);
	
	List<RolePermission> getPermiByRole(Role role);
	
	List<Role> getAllRole();
	
}
