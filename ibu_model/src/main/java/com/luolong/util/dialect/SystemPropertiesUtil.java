package com.luolong.util.dialect;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置system.properties
 * 
* <p>Description:   </p>
* <p>Company: 深圳市飞天网景通讯有限公司</p>
* <p>Copyright:  Copyright (c) 2012 </p>
* @author 何其安
* @date Nov 20, 2012
* @version V1.0
 */
public class SystemPropertiesUtil {
	private static Properties properties = new Properties();
	static{
		try {			
			properties.load(SystemPropertiesUtil.class.getResourceAsStream("/properties/system.properties"));
		} catch (IOException e) {
			System.out.println("加载资源文件system.properties出错，请检查文件system.properties是否在类路径上!");
			throw new RuntimeException("加载资源文件出错!",e);
		}
	}
	
	public static String get(String key){
		return properties.getProperty(key);
	}
	
}
