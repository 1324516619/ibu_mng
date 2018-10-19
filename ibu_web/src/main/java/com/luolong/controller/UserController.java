package com.luolong.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.luolong.common.RetObj;
import com.luolong.common.security.ShiroDbRealm;
import com.luolong.model.Role;
import com.luolong.model.ShiroUser;
import com.luolong.model.User;
import com.luolong.page.Pagination;
import com.luolong.service.RoleService;
import com.luolong.service.UserService;

@Controller  
public class UserController extends BaseController {
	
	// 日志记录器
    public final Logger log = Logger.getLogger(this.getClass());

    @Resource  
    private UserService userService;
    
    @Resource
    private RoleService roleService;
    
    @Resource
    private ShiroDbRealm shiroDbRealm;

    @RequestMapping("/login.do")    
    public ModelAndView login(User user){      
        ModelAndView mav = new ModelAndView("index");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getUserPassword());
        // 校验验证码是否正确
 		String rand = (String)subject.getSession().getAttribute("code");
 		if(null ==user.getInputRand() || (StringUtils.isNotBlank(rand)  && !user.getInputRand().toLowerCase().equalsIgnoreCase(rand))){
 			mav = new ModelAndView("login"); 
        	mav.addObject("isok", "验证码错误");
 			return mav;
 		}
 		//验证码校验通过后，清除验证码
         subject.getSession().removeAttribute("code");
        try {
        	// 验证是否登录成功
        	subject.login(token);
        	// 获取登录信息，保存到session中
        	ShiroUser shiroUser = (ShiroUser)subject.getPrincipals().fromRealm(shiroDbRealm.getName()).iterator().next();
        	subject.getSession().setAttribute("loginName", user.getUserName());
        	subject.getSession().setAttribute("loginInfo", shiroUser);
        	//菜单集合
        	mav.addObject("menuList", shiroUser.getMenuList());
			
		} catch (AuthenticationException uae) {
			uae.printStackTrace();
			mav = new ModelAndView("login"); 
        	mav.addObject("isok", "用户名或密码错误");
		}
        mav.addObject("user", user);
        return mav;    
    }
    
	@RequestMapping("/user/userList.do")
	public ModelAndView userList(User user) {
		ModelAndView mav = new ModelAndView("user/userList");
		//user.setUserName("l");
		Pagination page = userService.getPage(user, getCurrentPage(), getPageSize());
		setTotalSize(Long.valueOf(page.getTotalCount()).intValue());
		mav.addObject("page", page);
		mav.addObject("totalSize",getTotalSize());
		mav.addObject("pageSize", getPageSize());
		return mav;
	}
	
	@RequestMapping("/user/toAdd.do")
	public ModelAndView toAdd(User user) {
		ModelAndView mav = new ModelAndView("user/user-add");
		//所有角色
		List<Role> roleList = roleService.getAllRole();
		mav.addObject("roleList",roleList);
		if(user.getUserId() != null){//修改页面
			//用户
			user = userService.selectUserById(user.getUserId());
			//用户的角色
			List<Role> userRoleList = userService.getUserRoleByUser(user);
			mav.addObject("user",user);
			mav.addObject("userRoleList",userRoleList);
		}
		return mav;
	}
	
	@RequestMapping("/user/add.do")
	@ResponseBody
	public RetObj add(User user){
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			if(user.getUserId()==null){//新增
				userService.add(user);
			}else{//修改
				userService.edit(user);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			retObj.setFlag(false);
			retObj.setMsg("保存失败");
		}
		retObj.setFlag(true);
		return retObj;
	}
	
	@RequestMapping("/user/toExport.do")
	public String toExport(User user){
		user.setCreateId(getSessionUser().getUserId());//创建人
		user.setCreateName(getSessionUser().getNickName());//创建人昵称
		userService.exportUser(user);
		return "redirect:/exportdata/list.do"; 
		
	}

	
	
	@RequestMapping("/welcome.do") 
	public ModelAndView welcome() {
		ModelAndView mav = new ModelAndView("welcome");
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getSession().getAttribute("loginInfo");
		mav.addObject("user", shiroUser);
		return mav;
	}
    

}  