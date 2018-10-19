package com.luolong.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	/**
	 * 获取两个时间相差的月份数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static int getMonths(Date date1 ,Date date2){
		return 12*(date1.getYear()-date2.getYear())+date1.getMonth()-date2.getMonth();
	}
	
	/**
	    * 根据传入的时间参数获取毫秒差值
	    * @param format
	    * @return
	    */
	   public static long getMaginTimes(Date comparedTime){
		  return getMaginTimes(Calendar.getInstance().getTime() , comparedTime);
	   }
	   
	   public static long getMaginTimes(Date baseTime , Date comparedTime){
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(baseTime);
		   long base = cal.getTimeInMillis();
		   cal.setTime(comparedTime);
		   long end = cal.getTimeInMillis();
		   if(end<=base){
			   return 0;
		   }
		   return end - base;
	   }
	   
	public static Date getNowDate00(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	public static Date getNowDate59(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	/**获取月第一天 */
	public static Date getFirstDateOfMonth(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.getActualMinimum(c.DAY_OF_MONTH));
		 return c.getTime();
	}
	
	/**获取月最后 */
	public static Date getLastDateOfMonth(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.getActualMaximum(c.DAY_OF_MONTH));
		return c.getTime();
		 
	}
	public static Date addMonth(Date date,int month){
		if(null == date)
			date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}
	/**获取当前日期时间*/
	public static Date getCurrentDate(){
		return new Date();
	}
	
	
	
	/**
	 * 格式化日期
	 * 
	 * @param dateStr
	 *            字符型日期
	 * @param format
	 *            格式
	 * @return 返回日期
	 */
	public static java.util.Date parseDate(String dateStr, String format) {
		java.util.Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			String dt = dateStr;
//			String dt = dateStr.replaceAll("-", "/");
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
						"0");
			}
			date = (java.util.Date) df.parse(dt);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * @param dateStr
	 * @return Date
	 */
	public static java.util.Date parseDate(String dateStr) {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(StringUtils.isNotBlank(dateStr)){
				Date date =sdf.parse(dateStr);
				return date;				
			}else{
				return null;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
//		return parseDate(dateStr, "yyyy-MM-dd");
	}
	
	
	public static java.util.Date parse2Date(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static Date start2Date(String strDate){
		SimpleDateFormat sdf =new SimpleDateFormat("yy-MM-dd");
		try {
			Date date =sdf.parse(strDate);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date end2Date(String strDate){
		SimpleDateFormat sdf =new SimpleDateFormat("yy-MM-dd");
		try {
			Date beginDate =sdf.parse(strDate);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
			Date endDate = sdf.parse(sdf.format(date.getTime()));

			return endDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化输出日期
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return 返回字符型日期
	 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	 
	/**
	 * 返回年份
	 * 
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}

	/**
	 * 返回日份
	 * 
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回秒钟
	 */
	public static int getSecond(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

 

	/**
	 * 返回字符型时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型时间
	 */
	public static String getTime(java.util.Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 返回字符型日期时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期时间
	 */
	public static String getDateTime(java.util.Date date) {
		return format(date, "yyyy-MM-dd HH:mm");
	}
	
	public static String getDateTimeSec(java.util.Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	public static java.util.Date addHour(java.util.Date date, int hour) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) hour)  * 3600 * 1000);
		return c.getTime();
	}
	public static java.util.Date addSecond(java.util.Date date, int second) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) second)  * 1 * 1000);
		return c.getTime();
	}
	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	public static int diffHours(java.util.Date date, java.util.Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / ( 3600 * 1000));
	}
	
	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthBegin(String strdate) {
		java.util.Date date = parseDate(strdate);
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthEnd(String strdate) {
		java.util.Date date = parseDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}
	/**
	 * 取得当前月份的最后一天
	 * 
	 * @param 
	 *            
	 * @return Date
	 */
	public static Date getCurrentMonthEnd() {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		return calendar.getTime();
	}	
	
	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(java.util.Date date) {
		//添加判断
		if(null ==date){
			return "";
		}
		return formatDateByFormat(date, "yyyy-MM-dd");
	}
	
	public static String format2Date(java.util.Date date) {
		//添加判断
		if(null ==date){
			return "";
		}
		return formatDateByFormat(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String formatDateSimple(java.util.Date date) {
		//添加判断
		if(null ==date){
			return "";
		}
		return formatDateByFormat(date, "yyyyMMdd");
	}
	
	public static String formatDateTimeSimple(java.util.Date date) {
		//添加判断
		if(null ==date){
			return "";
		}
		return formatDateByFormat(date, "yyyyMMdd HHmm");
	}
	
	public static java.util.Date parseSimpleDate(String dateStr) {
		return parseDate(dateStr, "yyyyMMdd");
	}
	
	public static java.util.Date parseSimpleDateTime(String dateStr) {
		return parseDate(dateStr, "yyyyMMdd HHmm");
	}

	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormat(java.util.Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 去掉Date里面的时分秒
	 * @param Date
	 * @return 去掉时分秒后的Date，若参数为空则返回原日期
	 */
	public static Date trimHmsForDate(Date date) {
		Date result = date;
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.clear(Calendar.MINUTE);
			cal.clear(Calendar.SECOND);
			cal.clear(Calendar.MILLISECOND);
			result = cal.getTime();
		}	
		return result;
	}
	
	/**
	 * 计算日期差
	 * @param startDate: 日期参数1
	 * @param endDate: 日期参数2
	 * @param diffType: 差值计算类别(1:秒数/2:分钟数/3:小时数/4:天数/5:周数/6:月数/7:年数)
	 * @return
	 */
	public static long getDateDiff(Date startDate, Date endDate, int diffType){
		long diffVal = -1;
		if (startDate.after(endDate)) return diffVal;
		
		Calendar firstDay = Calendar.getInstance();
        Calendar lastDay = Calendar.getInstance();
        firstDay.setTime(startDate);
        lastDay.setTime(endDate);
        
        long fact = 1000;
        switch (diffType){
        	case 1: //计算秒数差值
		        break;
        	case 2: //计算分钟数差值
        		fact = fact * 60;
		        break;
        	case 3: //计算小时差值
        		fact = fact * 60 * 60;
		        break;
        	case 4: //计算天数差值
        		fact = fact * 24 * 60 * 60;
		        break;
		    default://计算秒数差值
        }
		diffVal = (lastDay.getTimeInMillis() - firstDay.getTimeInMillis())/fact;
        return diffVal;		
	}

	/** 
	* 
	* @Description:判断<firstDate>时间点是否在<secondDate>时间点之前 
	* 如果此 firstDate 的时间在参数<secondDate>表示的时间之前，则返回小于 0 的值 
	* @param firstDate 
	* @param secondDate 
	* @return 
	* @ReturnType boolean 
	* @author: 
	* @Created 2012 2012-9-20上午08:40:33 
	*/ 
	public static boolean isBefore(Date firstDate, Date secondDate) { 

		return compare(firstDate, secondDate) < 0 ? true : false; 
	} 
	
	/** 
	* 
	* @Description:比较两个时间点 
	* 如果secondDate表示的时间等于此 firstDate 表示的时间，则返回 0 值； 
	* 如果此 firstDate 的时间在参数<secondDate>表示的时间之前，则返回小于 0 的值； 
	* 如果此 firstDate 的时间在参数<secondDate>表示的时间之后，则返回大于 0 的值 
	* @param firstDate 
	* @param secondDate	
	* @ReturnType int 
	* @author: 
	* @Created 2012 2012-9-20上午08:34:33 
	*/ 
	public static int compare(Date firstDate, Date secondDate) { 

		Calendar firstCalendar = null; 
		/**使用给定的 Date 设置此 Calendar 的时间。**/ 
		if (firstDate != null) { 
			firstCalendar = Calendar.getInstance(); 
			firstCalendar.setTime(firstDate); 
		} 

		Calendar secondCalendar = null; 
		/**使用给定的 Date 设置此 Calendar 的时间。**/ 
		if (secondDate != null) { 
			secondCalendar = Calendar.getInstance(); 
			secondCalendar.setTime(secondDate); 
		} 

		try { 
			/** 
			* 比较两个 Calendar 对象表示的时间值（从历元至现在的毫秒偏移量）。 
			* 如果参数表示的时间等于此 Calendar 表示的时间，则返回 0 值； 
			* 如果此 Calendar 的时间在参数表示的时间之前，则返回小于 0 的值； 
			* 如果此 Calendar 的时间在参数表示的时间之后，则返回大于 0 的值 
			* **/ 
			return firstCalendar.compareTo(secondCalendar); 
		} catch (NullPointerException e) { 
			throw new IllegalArgumentException(e); 
		} catch (IllegalArgumentException e) { 
			throw new IllegalArgumentException(e); 
		} 
	} 

	/** 
     * 获取现在时间 
     * 
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss 
     */ 
     public static java.sql.Timestamp getNowSqlDate() { 
	     
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	      
	     return java.sql.Timestamp.valueOf(formatter.format(new Date())); 
	     
     } 
	/**
	 * 时间往后退*个月份,并且返回的类型为字符串
	 * @param args
	 */
	public static String backDate(Date date , int i){
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM"); 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.add(Calendar.MONTH, i); 
		return sdf.format(cal.getTime());	
	}
	
	/**
	 * 时间往后退*个月份,并且返回的类型为字符串
	 * @param args
	 */
	public static String backDateAll(Date date , int i){
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd"); 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.add(Calendar.MONTH, i); 
		return sdf.format(cal.getTime());	
	}
	
	/**
	 * 今天还剩多少秒
	 * @author: luolong
	 * @return
	 */
    public static int getMiao(){
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate
                .get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
                .get(Calendar.DATE) + 1, 0, 0, 0);
        return (int)(tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000;
    }
    
    /**
     * 获取某个时间点的时间戳
     * @author: 胡庆伟
     * @param date
     * @return
     */
    public static long getTimeseamp(String date){
        Date date1 = null;
        date1 = parse2Date(date);
        return date1.getTime();
    }
    
    /**
     * 时间戳转日期
     * @author: 胡庆伟
     * @param date
     * @return
     */
    public static String getTimeseampToDate(long time){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return format.format(time);  
    }
    
    
	public static void main(String[] args) {
 
		 //System.out.println(getFirstDateOfMonth());
		// System.out.println(getLastDateOfMonth());
//		Date nextMonth = DateUtil.addMonth(new Date(),1);
//		int a=  DateUtil.diffDate(nextMonth, new Date());
//		System.out.println(a);
		String str = "2014-04-01";
		Date date = DateUtil.start2Date(str);
		String test = DateUtil.backDate(date, -5);
		System.out.println(test);
	}
	
}
