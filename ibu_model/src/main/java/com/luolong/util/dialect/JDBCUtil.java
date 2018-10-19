package com.luolong.util.dialect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * jdbc 工具类
 * @author: husy
 * @since: 2017年5月3日下午5:29:29 
 * @version: 1.0
 */
public class JDBCUtil {  
  
    private static final Logger log = LoggerFactory.getLogger(JDBCUtil.class);
    
    private static Connection conn = null;  
    private static CallableStatement cs = null;
    private static PreparedStatement pstmt=null;
    private static ResultSet rs=null;
  
    private static Connection getConn(){
        try {
            String url = JDBCPropertiesUtil.get("jdbc.url");  
            String name = JDBCPropertiesUtil.get("jdbc.driver");  
            String user = JDBCPropertiesUtil.get("jdbc.username");  
            String password = JDBCPropertiesUtil.get("jdbc.password");  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return conn;
    }
    
    public static void execProc(String sql){
        log.info("执行存储过程>>>>>>>>>>>>>>>" + sql);
        Connection conn = getConn();
        CallableStatement cs = null;
        try{
            cs = conn.prepareCall(sql);
            cs.execute();
            log.info("执行存储过程成功>>>>>>>>>>>>>>>");
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(null != cs){
                    cs.close();
                }
                if(null != conn){
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
   
    /**
     * 执行查询部门及子部门函数
     * @author: 黄泽亮
     * @param sql
     * @return
     */
    public static String execFunc(String sql){
        log.info("执行函数开始>>>>>>>>>>>>>>>" + sql);
        Connection conn = getConn();
        PreparedStatement pst = null;
        try{
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            log.info("执行函数成功>>>>>>>>>>>>>>>");
            String result = null;
            while(rs.next())
            {
            result=rs.getString(1);
            }
            return result;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(null != pst){
                    pst.close();
                }
                if(null != conn){
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return "";
    }
    
     
    /**
     * 
     */
    public static List<Map<String, Object>> findDataBySql(String searchSql){
        Connection conn = getConn();
        List<Map<String, Object>> list = null;
        Statement statement = null ;
        ResultSet rs = null;
        try{
            statement = conn.createStatement();
            rs = statement.executeQuery(searchSql);
            ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
            int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
            list = new ArrayList<Map<String, Object>>();
            Map<String, Object> rowData = new HashMap<String, Object>();
            while(rs.next()){
                rowData = new HashMap<String, Object>(columnCount);
                for(int i = 1; i <= columnCount; i++){
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(null != rs){
                    rs.close();
                }
                if(null != statement){
                    statement.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return list;
    }
    public static int findCountBySql(String countSql){
        Connection conn = getConn();
        Statement statement = null ;
        ResultSet rs = null;
        try{
            statement = conn.createStatement();
            rs = statement.executeQuery(countSql);
            int count = 0;
            if(rs.next()){
                count = rs.getInt("rowCount");
            }
            return count;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(null != rs){
                    rs.close();
                }
                if(null != statement){
                    statement.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return 0;
    }
    /**
     * 调用的存储过程
     * @param prc:过程语句
     * @param paramsIn：入参列表
     * @param paramsOut：出参列表
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static List<String> execProcedure(String prc,Map<Integer,Object> paramsIn,Map<Integer,String> paramsOut){
        List<String> list=new ArrayList<String>();
        String pro="{call "+prc+"}";

        conn = getConn();
        log.info("-------------conn:"+conn);
        int key;
        String typename;
        Set keys;
        ResultSetMetaData md=null;
        
        try {
            cs=conn.prepareCall(pro);
            //设置过程的参数
            setParams(cs,paramsIn);
            log.info("-------------开始执行");
            cs.execute();
            
            log.info("-------------执行结束");
            if(paramsOut!=null&&paramsOut.size()>0){
                keys=paramsOut.keySet();  //取出所有入参的键，即入参对应的问号的序号
                if(keys!=null){
					Iterator iterator=keys.iterator();
                    while(iterator.hasNext()){
                        key=(Integer) iterator.next();
                        typename=(String) paramsOut.get(key);      //3,varchar  4, cursor

                        log.info("-------------typename:"+typename);
                        //判断值的数据类型
                        try {
                            if("cursor".equals(typename)){
                                rs=(ResultSet) cs.getObject(key);  //1 a 24  2 b 23
                                md=rs.getMetaData();
                                while(rs.next()){
                                    for(int i=0;i<md.getColumnCount();i++){
                                        list.add(rs.getString(i+1));
                                    }
                                }
                            }else if ("int".equals(typename)){
                                list.add(cs.getInt(key)+"");
                                log.info("-------------typename:"+cs.getInt(key));
                            }else{
                            	log.info("-------------typename:"+cs.getString(key));
                                list.add(cs.getString(key));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeAll(conn,null,rs,cs);
        }
        return list;
    }
    /**
     * 设置存储过程的参数
     * @param cs
     * @param paramsIn
     * @param paramsOut
     */
    @SuppressWarnings("rawtypes")
    private static void setParams(CallableStatement cs,Map<Integer,Object> paramsIn){
        int key=0; //对应的问号的序号
        Object value=null;

        String attrType;
        Set keys;  //所有的键
        if(paramsIn!=null&&paramsIn.size()>0){
            keys=paramsIn.keySet();  //取出所有入参的键，即入参对应的问号的序号1 2 3 4
            if(keys!=null){
                Iterator iterator=keys.iterator();
                while(iterator.hasNext()){
                    key=(Integer) iterator.next();
                    value=paramsIn.get(key);      //1,10001
                    attrType=value.getClass().getName(); //获取值数据类型
                    log.info("-------------attrType："+attrType);
                    //判断值的数据类型
                    try {
                        if("java.lang.Integer".equals(attrType)){
                            cs.setInt(key,(Integer)value);
                        }else if("java.lang.String".equals(attrType)){
                            cs.setString(key,(String)value);
                        }else if("java.lang.Double".equals(attrType)){
                            cs.setDouble(key,(Double)value);
                        }else if("java.lang.Long".equals(attrType)){
                            cs.setLong(key,(Long)value);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    /**
     * 关闭的方法
     */
    private static void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs,CallableStatement cs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
        
        if(cs!=null){
            try {
                cs.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
        
        if(pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
        
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                log.error(e.toString());
            }
        }
    }
}  