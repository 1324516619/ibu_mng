package com.luolong.util;

public class IbuStringUtil {

	  public static String valueOf(Object obj) {
		  if(null==obj){
			  if(obj instanceof Long
					  ||obj instanceof Double
					  ||obj instanceof Float
					  ||obj instanceof Integer){
				  return  IbuStringUtil.valueOf(0);
			  }
			  return "";
		  }else{
			  return obj.toString();
		  }
	   }
	  public static String getCurrPage(Object obj) {
		  if(null==obj){
			  if(obj instanceof Long
					  ||obj instanceof Double
					  ||obj instanceof Float
					  ||obj instanceof Integer){
				  return  IbuStringUtil.valueOf(obj);
			  }
			  return "1";
		  }else{
			  return obj.toString();
		  }
	   }
	  
	  public static Long longValueOf(Object obj) {
		  try{
			  if(null==obj){
				  return 0L;
			  }else{
				    if(obj instanceof Double){
				    	return  Double.valueOf(obj.toString()).longValue();
				    }
				    if(obj instanceof Float){
				    	return  Float.valueOf(obj.toString()).longValue();
				    }
				    return  Long.valueOf(obj.toString());
				  }
		  }catch(Exception e){
			 // e.printStackTrace();
			  try{
			      if(null != obj){
			          return Double.valueOf(obj.toString()).longValue();
			      }
			  }catch(Exception ee){
				  
			  }
			  return 0L;
		  }
	   }
	  
	  
	  
	  
	  
	  
	  
}
