package com.luolong.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 安全相关的工具类
 * 
 * @author: 研发部-马园园
 * @since: 2017年8月29日上午9:48:46 
 * @version: 1.0
 */
public class SafetyUtil {

    private static final Set<String> SPECIAL_CHARS;
    private static final String REGEX_USER_NAME = "^[a-zA-Z0-9_@-]{1,50}$";
    private static final String REGEX_TAG = "(<)([^<>]*)(>)";
    static{
        SPECIAL_CHARS = new HashSet<String>();
        SPECIAL_CHARS.add("\\r");
        SPECIAL_CHARS.add("%0d");
        SPECIAL_CHARS.add("\\n");
        SPECIAL_CHARS.add("%0a");
    }

    public static String dealHeaderValue(String str){
        for(String tmp : SPECIAL_CHARS){
            str = str.replaceAll(tmp, "");
        }
        return str;
    }

    public static boolean isLegalUsername(String username){
        if(username==null){
            return false;
        }
        return username.matches(REGEX_USER_NAME);
    }
    
    public static String simpleHtmlTransfer(String str){
        if(StringUtils.isBlank(str)){
            return str;
        }
        str.replaceAll("\\r", "");
        str.replaceAll("%0d", "");
        str.replaceAll("\\n", "");
        str.replaceAll("%0a", "");
        Pattern p = Pattern.compile(REGEX_TAG);
        Matcher m = p.matcher(str);
        if(m.find()){
            str=str.replaceAll("<", "&lt;");
            str=str.replaceAll(">", "&gt;");
        }
        return str;
    }
    
    public static void main(String[] args){
        String str="<a  <h>aa</h> b  c>def</abc>";
        Pattern p = Pattern.compile(REGEX_TAG);
        Matcher m = p.matcher(str);
        System.out.println("*********************");
        while(m.find()){
            System.out.println("groupCount:"+m.groupCount());
            for(int i=0;i<=m.groupCount();i++){
                System.out.println(m.group(i));
            }
            str=str.replaceAll(m.group(0), "&lt;"+m.group(2)+"&gt;");
        }
        System.out.println(str);
    }
}
