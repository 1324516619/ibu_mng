package com.luolong.util.dialect;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 读取配置jdbc.properties
 * @author: 胡庆伟
 * @since: 2016年6月29日下午5:21:34 
 * @version: 1.0
 */
public class JDBCPropertiesUtil {
    private static final Log log = LogFactory.getLog(JDBCPropertiesUtil.class);
    private static Properties properties = new Properties();
    static{
        try{
            properties.load(JDBCPropertiesUtil.class.getResourceAsStream("/jdbc.properties"));
        }catch(IOException e){
            log.error("加载资源文件jdbc.properties出错，请检查文件jdbc.properties是否在类路径上!");
            throw new RuntimeException("加载资源文件出错!", e);
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }
}
