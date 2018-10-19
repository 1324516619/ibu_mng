package com.luolong.common.taglib;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

import com.luolong.model.Permission;
import com.luolong.model.ShiroUser;

/**
 * 判断是否有权限
 * 
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: 深圳市飞天网景通讯有限公司
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * 
 * @author 何其安
 * @date Jan 14, 2013
 * @version V1.0
 */
public class HasPermissionTag extends PermissionTag {

	private static final long serialVersionUID = 9087214251933699804L;

	protected boolean showTagBody(String p) {

		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getSession().getAttribute(
				"loginInfo");
		if (null != shiroUser && shiroUser.getUserId() == 1) {
			return true;
		}

		if ((p == null) || ("".equals(p)))
			return false;
		List<Permission> permiList = shiroUser.getPermiList();
		for(Permission per : permiList){
			if(per.getPermiValue().equals(p)){
				return true;
			}
		}
		return false;
	}
}