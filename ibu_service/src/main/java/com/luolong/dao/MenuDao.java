package com.luolong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.luolong.model.Menu;
import com.luolong.model.Order;
import com.luolong.model.User;
import com.luolong.util.dialect.Page;

public interface MenuDao {
	public List<Menu> queryAllMenu();
	
	public List<Menu> queryUserMenu(User user);
	
	List<Order> getPage(@Param("page") Page<Menu> page,@Param("menu") Menu menu);
	
	int add(Menu menu);
	
	int edit(Menu menu);
	
	Menu getMenu(Menu menu);
}
