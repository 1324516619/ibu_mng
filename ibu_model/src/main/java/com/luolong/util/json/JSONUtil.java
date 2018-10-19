package com.luolong.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

    /** 
     * 从一个JSON 对象字符格式中得到一个java对象  
     * @param jsonString  
     * @param pojoCalss  
     * @return  
     */  
    @SuppressWarnings("rawtypes")
    public static Object getObject4JsonString(String jsonString,Class pojoCalss){   
        Object pojo;   
        JSONObject jsonObject = JSONObject.fromObject( jsonString );     
        pojo = JSONObject.toBean(jsonObject,pojoCalss);   
        return pojo;   
    } 
    
    /**   
     * 从json对象集合表达式中得到一个java对象列表  
     * @param jsonString  
     * @param pojoClass  
     * @return  
     */  
    @SuppressWarnings("rawtypes")
    public static List getList4Json(String jsonString, Class pojoClass){   

        JSONArray jsonArray = JSONArray.fromObject(jsonString);   
        JSONObject jsonObject;   
        Object pojoValue;   

        List list = new ArrayList();   
        for ( int i = 0 ; i<jsonArray.size(); i++){   

            jsonObject = jsonArray.getJSONObject(i);   
            pojoValue = JSONObject.toBean(jsonObject,pojoClass);   
            list.add(pojoValue);   

        }   
        return list;   

    } 


    /**  
     * 从json数组中解析出java字符串数组  
     * @param jsonString  
     * @return  
     */  
    public static String[] getStringArray4Json(String jsonString){   
        JSONArray jsonArray = JSONArray.fromObject(jsonString);   
        String[] stringArray = new String[jsonArray.size()];   
        for( int i = 0 ; i<jsonArray.size() ; i++ ){   
            stringArray[i] = jsonArray.getString(i);   

        }   

        return stringArray;   
    } 


    /*//**  
     * 从json数组中得到相应java数组  
     * @param jsonString  
     * @return  
     */  
    public static Object[] getObjectArray4Json(String jsonString){   
        JSONArray jsonArray = JSONArray.fromObject(jsonString);   
        return jsonArray.toArray();   

    }   
    
    /** 
     * 从json HASH表达式中获取一个map，改map支持嵌套功能  
     * @param jsonString  
     * @return  
     */ 
    @SuppressWarnings("rawtypes")
    public static Map<String,Object> getMap4Json(String jsonString){  
        if(null ==jsonString||"".equals(jsonString))
            return new HashMap<String,Object>();
        JSONObject jsonObject = JSONObject.fromObject( jsonString );     
        Iterator  keyIter = jsonObject.keys();   
        String key;   
        Object value;   
        Map<String,Object> valueMap = new HashMap<String,Object>();   
  
        while( keyIter.hasNext()) {   
            key = (String)keyIter.next();   
            value = jsonObject.get(key);   
            valueMap.put(key, value);   
        }   
           
        return valueMap;   
    }



    /**   
     * 从json数组中解析出javaLong型对象数组  
     * @param jsonString  
     * @return  
     */  
    public static Long[] getLongArray4Json(String jsonString){   

        JSONArray jsonArray = JSONArray.fromObject(jsonString);   
        Long[] longArray = new Long[jsonArray.size()];   
        for( int i = 0 ; i<jsonArray.size() ; i++ ){   
            longArray[i] = jsonArray.getLong(i);   

        }   
        return longArray;   
    }   

    /**   
     * 从json数组中解析出java Integer型对象数组  
     * @param jsonString  
     * @return  
     */  
    public static Integer[] getIntegerArray4Json(String jsonString){   

        JSONArray jsonArray = JSONArray.fromObject(jsonString);   
        Integer[] integerArray = new Integer[jsonArray.size()];   
        for( int i = 0 ; i<jsonArray.size() ; i++ ){   
            integerArray[i] = jsonArray.getInt(i);   

        }   
        return integerArray;   
    }   


    /**  
     * 从json数组中解析出java Integer型对象数组  
     * @param jsonString  
     * @return  
     */  
    public static Double[] getDoubleArray4Json(String jsonString){   

        JSONArray jsonArray = JSONArray.fromObject(jsonString);   
        Double[] doubleArray = new Double[jsonArray.size()];   
        for( int i = 0 ; i<jsonArray.size() ; i++ ){   
            doubleArray[i] = jsonArray.getDouble(i);   
        }   
        return doubleArray;   
    }   


    /**  
     * 将java对象转换成json字符串  
     * @param javaObj  
     * @return  
     */  
    public static String getJsonString4JavaPOJO(Object javaObj){   

        JSONObject json;   
        json = JSONObject.fromObject(javaObj);   
        return json.toString();   

    }   
    
    /**
     * 将json中的特殊字符解析（与客户端约定）
     * #2@5#表示%，#2@6#表示&
     * @param numeric
     * @return
     */
    public static String getSpecialNumeric(String numeric){
    	if(StringUtils.isBlank(numeric)){
    		return null;
    	}
        numeric =numeric.replaceAll("#2@5#", "%").replaceAll("#2@6#", "&");
        return numeric;
    }
    
    /**
     * String转Json
     * @param data
     * @return
     */
    public static JSONObject StringToJson(String data){
        try {
            return JSONObject.fromObject(data); 
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * String转map
     * @param jsonString
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> StringToMap(String jsonString){  

        try {
            JSONObject jsonObject = JSONObject.fromObject( jsonString );     
            Iterator  keyIter = jsonObject.keys();   
            Map<String, Object> valueMap = new HashMap<String, Object>();   
  
            while( keyIter.hasNext()) {   
                String key = keyIter.next().toString();   
                String value = jsonObject.get(key).toString();   
                valueMap.put(key, value);   
            }
            return valueMap;   
        } catch (Exception e) {
            return null;
        }   
           
    }
    
    
    /**
     * String转map
     * @param jsonString
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<Object, Object> StringToObjectMap(String jsonString){  

        try {
            JSONObject jsonObject = JSONObject.fromObject( jsonString );     
            Iterator  keyIter = jsonObject.keys();   
            Map<Object, Object> valueMap = new HashMap<Object, Object>();   
  
            while( keyIter.hasNext()) {   
                String key = keyIter.next().toString();   
                String value = jsonObject.get(key).toString();   
                valueMap.put(key, value);   
            }
            return valueMap;   
        } catch (Exception e) {
            return null;
        }   
           
    }
    public static void main(String[] args) {
       /* String numeric ="5464646#2@5#";
        System.out.println("1:"+numeric);
        numeric =numeric.replaceAll("#2@5#", "%").replaceAll("#2@6#", "&");;
        System.out.println("2:"+numeric);*/
        String a ="{\"type\":\"城际高速\",\"station\":\"北京南\",\"stationNO\":\"1\",\"days\":\"1\",\"arriveTime\":\"-\",\"startDriveTime\":\"08:45\",\"km\":\"0\"},{\"type\":\"城际高速\",\"station搜索\":\"天津\",\"stationNO\":\"2\",\"days\":\"1\",\"arriveTime\":\"09:15\",\"startDriveTime\":\"-\",\"km\":\"120\"}";
        Map<Object,Object> m = StringToObjectMap(a);
        System.out.println(m.size());
        for(Object obj : m.keySet()) {
        	 System.out.println(obj.toString());
        } 
    }
}
