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
import com.luolong.model.Permission;
import com.luolong.model.Role;
import com.luolong.page.Pagination;
import com.luolong.service.MenuService;
import com.luolong.service.PermissionService;
import com.luolong.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	// 日志记录器
	public final Logger log = Logger.getLogger(this.getClass());
	@Resource
	private RoleService roleService;
	
	@Resource
	private MenuService menuService;
	
	@Resource
	private PermissionService permissionService;

	@RequestMapping("/roleList.do")
	public ModelAndView menuList(Role role) {
		ModelAndView mav = new ModelAndView("/role/roleList");
		Pagination page = roleService.getPage(role, getCurrentPage(),
				getPageSize());
		setTotalSize(Long.valueOf(page.getTotalCount()).intValue());
		mav.addObject("page", page);
		mav.addObject("totalSize", getTotalSize());
		mav.addObject("pageSize", getPageSize());
		return mav;
	}
	
	@RequestMapping("/toAdd.do")
	public ModelAndView toAdd(Role role){
		ModelAndView mav = new ModelAndView("/role/role-add");
		//菜单集合
		List<Menu> menuList = menuService.queryAllMenu();
		//权限集合
		List<Permission> permiList = permissionService.queryAllPermission();
		mav.addObject("menuList", menuList);
		mav.addObject("permiList", permiList);
		if(role.getRoleId() !=0){//修改页面
			role = roleService.getRoleById(role);
			mav.addObject("role", role);
		}
		return mav;
		
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public RetObj add(Role role){
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		role.setCreator(getSessionUser().getUserName());
		try {
			if(role.getRoleId()==0){//新增
				roleService.add(role);
			}else{//修改
				roleService.edit(role);
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