package com.luolong.service;

import java.util.List;

import com.luolong.model.Menu;
import com.luolong.model.User;
import com.luolong.page.Pagination;

public interface MenuService {
	
	List<Menu>  queryUserMenu(User user);
	
	List<Menu> queryAllMenu();
	
	/**
     * 分页查询
     */
    Pagination getPage(Menu menu, int currentPage, int pageSize);
    
    int add(Menu menu);
    
    int edit(Menu menu);
    
    Menu getMenu(Menu menu);

}
