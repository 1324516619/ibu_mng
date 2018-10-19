package com.luolong.common.security;

import com.luolong.model.ShiroUser;

public class SimpleAuthenticationInfoUtil extends org.apache.shiro.authc.SimpleAuthenticationInfo  {
	private static final long serialVersionUID = -5260111254489044842L;
	public static String DEFAULT_PWD="6ad685d2b25f38613614f963d3f8f203";
	 
     public SimpleAuthenticationInfoUtil(){
    	 
     }
	 public SimpleAuthenticationInfoUtil(ShiroUser u, String pwd,
				String name) {
		  super(u,pwd,name);
		}
}
