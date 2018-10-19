package com.luolong.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luolong.dao.RoleDao;
import com.luolong.model.Role;
import com.luolong.model.RoleMenu;
import com.luolong.model.RolePermission;
import com.luolong.page.Pagination;
import com.luolong.util.dialect.Page;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Pagination getPage(Role role, int currentPage, int pageSize) {
		Page<Role> page = new Page<Role>(currentPage, pageSize);
        List<Role> list = roleDao.getPage(page, role);
        Pagination p = new Pagination(currentPage, pageSize, page.getTotalCount());
        p.setList(list);
        return p;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int add(Role role) {
		//保存角色
		int flag  = roleDao.add(role);
		
		//保存角色菜单
		if(StringUtils.isNotEmpty(role.getMenuArr())){
			List<String> menuIds = Arrays.asList(role.getMenuArr().split(","));
			RoleMenu roleMenu = null;
			if(menuIds.size() > 0){
				for(int i=0;i<menuIds.size();i++){
					roleMenu = new RoleMenu();
					roleMenu.setRoleId(role.getRoleId());
					roleMenu.setMenuId(Integer.parseInt(menuIds.get(i)));
					roleDao.addRoleMenu(roleMenu);
				}
			}
		}
		
		//保存角色权限
		if(StringUtils.isNotEmpty(role.getPermiArr())){
			List<String> permiIds = Arrays.asList(role.getPermiArr().split(","));
			RolePermission rolePermission = null;
			if(permiIds.size() > 0){
				for(int i=0;i<permiIds.size();i++){
					rolePermission = new RolePermission();
					rolePermission.setRoleId(role.getRoleId());
					rolePermission.setPermission(permiIds.get(i));
					roleDao.addRolePermission(rolePermission);
				}
			}
		}
		
		return flag;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int edit(Role role) {
		//角色
		int flag = roleDao.edit(role);
		
		//角色菜单
			//删除
		roleDao.delRoleMenu(role);
		
		    //新增
		if(StringUtils.isNotEmpty(role.getMenuArr())){
			List<String> menuIds = Arrays.asList(role.getMenuArr().split(","));
			RoleMenu roleMenu = null;
			if(menuIds.size() > 0){
				for(int i=0;i<menuIds.size();i++){
					roleMenu = new RoleMenu();
					roleMenu.setRoleId(role.getRoleId());
					roleMenu.setMenuId(Integer.parseInt(menuIds.get(i)));
					roleDao.addRoleMenu(roleMenu);
				}
			}
		}
		
		//角色权限
			//删除
		roleDao.delRolePermission(role);
			
		    //新增
		if(StringUtils.isNotEmpty(role.getPermiArr())){
			List<String> permiIds = Arrays.asList(role.getPermiArr().split(","));
			RolePermission rolePermission = null;
			if(permiIds.size() > 0){
				for(int i=0;i<permiIds.size();i++){
					rolePermission = new RolePermission();
					rolePermission.setRoleId(role.getRoleId());
					rolePermission.setPermission(permiIds.get(i));
					roleDao.addRolePermission(rolePermission);
				}
			}
		}
		
		return flag;
	}
	
	@Override
	public Role getRoleById(Role role) {
		//角色
		role = roleDao.getRoleById(role);
		//角色菜单
		List<RoleMenu> menuList = roleDao.getMenuByRole(role);
		for(RoleMenu m : menuList){
			role.addMenuId(m.getMenuId().longValue());
		}
		
		//角色权限
		List<RolePermission> permiList = roleDao.getPermiByRole(role);
		for(RolePermission p : permiList){
			role.addPermission(p.getPermission());
		}
		return role;
	}
	
	@Override
	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}
	
}
