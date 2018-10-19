package com.luolong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luolong.dao.MenuDao;
import com.luolong.model.Menu;
import com.luolong.model.Order;
import com.luolong.model.User;
import com.luolong.page.Pagination;
import com.luolong.util.dialect.Page;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao;

	@Override
	public List<Menu> queryUserMenu(User user) {
		List<Menu> menuList = null;
		// 管理员
		if (user.getUserId() == 1) {
			menuList = menuDao.queryAllMenu();
			menuList = dgMenuList(menuList,0);
		}
		else{
			menuList = menuDao.queryUserMenu(user);
			menuList = dgMenuList(menuList,0);
		}
		return menuList;
	}
	
	@Override
	public List<Menu> queryAllMenu() {
		List<Menu> menuList = null;
		menuList = menuDao.queryAllMenu();
		menuList = dgMenuList(menuList,0);
		return menuList;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int add(Menu menu) {
		int flag = menuDao.add(menu);
		return flag;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int edit(Menu menu) {
		int flag = menuDao.edit(menu);
		return flag;
	}
	
	@Override
	public Menu getMenu(Menu menu) {
		return menuDao.getMenu(menu);
	}
	
	@Override
	public Pagination getPage(Menu menu, int currentPage, int pageSize) {
		Page<Menu> page = new Page<Menu>(currentPage, pageSize);
        List<Order> list = menuDao.getPage(page, menu);
        Pagination p = new Pagination(currentPage, pageSize, page.getTotalCount());
        p.setList(list);
        return p;
	}
	
	/**
	 * 递归
	 * @param menuList
	 * @param parentMenuId
	 * @return
	 */
	private List<Menu> dgMenuList(List<Menu> menuList,int parentMenuId ){
		 List<Menu> childList = new ArrayList<Menu>();
		 for (Menu c : menuList)
	        {
                int id = c.getMenuId();
                int pid = c.getParentMenuId();
                if (parentMenuId==pid)
                {
                    List<Menu> childs = dgMenuList(menuList, id);
                    c.setChildList(childs);
                    childList.add(c);
                }
	        }
	        return childList;
	}
}
