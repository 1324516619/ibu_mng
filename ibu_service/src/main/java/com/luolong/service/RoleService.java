package com.luolong.service;

import java.util.List;

import com.luolong.model.Role;
import com.luolong.page.Pagination;

public interface RoleService {
	
	/**
     * 分页查询
     */
    Pagination getPage(Role role, int currentPage, int pageSize);
    
    int add(Role role);
    
    Role getRoleById(Role role);
    
    int edit(Role role);
    
    List<Role> getAllRole();
    
    

}
