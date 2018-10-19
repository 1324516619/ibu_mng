package com.luolong.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.luolong.common.RetObj;
import com.luolong.model.Menu;
import com.luolong.page.Pagination;
import com.luolong.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	// 日志记录器
	public final Logger log = Logger.getLogger(this.getClass());
	@Resource
	private MenuService menuService;

	@RequestMapping("/menuList.do")
	public ModelAndView menuList(Menu menu) {
		ModelAndView mav = new ModelAndView("/menu/menuList");
		Pagination page = menuService.getPage(menu, getCurrentPage(),
				getPageSize());
		setTotalSize(Long.valueOf(page.getTotalCount()).intValue());
		mav.addObject("page", page);
		mav.addObject("totalSize", getTotalSize());
		mav.addObject("pageSize", getPageSize());
		return mav;
	}

	@RequestMapping("/toAdd.do")
	public ModelAndView toAdd(Menu menu) {
		ModelAndView mav = new ModelAndView("/menu/menu-add");
		List<Menu> menuList = menuService.queryAllMenu();
		mav.addObject("menuList", menuList);
		//修改页面
		if(menu.getMenuId()!=0){
			Menu menuObj =  menuService.getMenu(menu);
			mav.addObject("menu", menuObj);
		}
		return mav;

	}

	@RequestMapping("/add.do")
	@ResponseBody
	public RetObj add(Menu menu){
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			//修改
			if(menu.getMenuId()!=0){
				menuService.edit(menu);
			}else{
				menuService.add(menu);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			retObj.setFlag(false);
			retObj.setMsg("保存失败");
		}
		retObj.setFlag(true);
		return retObj;
	}
}