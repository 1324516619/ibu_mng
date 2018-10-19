package com.luolong.common.security;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.luolong.model.Menu;
import com.luolong.model.Permission;
import com.luolong.model.ShiroUser;
import com.luolong.model.User;
import com.luolong.service.MenuService;
import com.luolong.service.PermissionService;
import com.luolong.service.UserService;

/**
 * 自实现用户与权限查询.
 * 密码用MD5加密
 */
public class ShiroDbRealm extends AuthorizingRealm {
	
	@Resource
	private UserService userService;
	
	@Resource
	private MenuService menuService;
	
	@Resource
	private PermissionService permissionService;

    private static final String ALGORITHM = "MD5";

    /**
     * 认证回调函数, 登录时调用.
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException{
    	UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        ShiroUser shiroUser = new ShiroUser();
        User u = new User();
        u.setUserName(token.getUsername());
        User user = userService.selectUser(u);
        String pwd = new Md5Hash(token.getPassword()).toHex();
        if(!(new Md5Hash(user.getUserPassword()).toHex()).equals(pwd)){ throw new AuthenticationException("密码错误!"); }
        try{
            BeanUtils.copyProperties(shiroUser, user);
        }catch(Exception e){
        }
        //用户菜单
        List<Menu> menuList = menuService.queryUserMenu(user);
        //用户权限
        List<Permission> permiList = permissionService.queryUserPermission(user);
        shiroUser.setMenuList(menuList);
        shiroUser.setPermiList(permiList);
        return new SimpleAuthenticationInfoUtil(shiroUser, pwd, getName());
    }
    
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}


    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal){
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo(){
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if(cache != null){
            for(Object key : cache.keys()){
                cache.remove(key);
            }
        }
    }

    @PostConstruct
    public void initCredentialsMatcher(){// MD5加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
        setCredentialsMatcher(matcher);
    }


}
