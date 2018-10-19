package com.luolong.controller;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.luolong.model.ShiroUser;
import com.luolong.util.IbuStringUtil;
import com.luolong.util.json.JSONUtil;

public class BaseController {
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private  HttpServletResponse response;
	
	private int currentPage = 1;//默认第一页	
	

    private int pageSize = 15;//修改为默认 15条
    
    private int totalSize = 0;
    
    /**
     * 获取默认的分页大小
     * @author: user
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }   

    /**
     * 设置分页大小
     * @author: user
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    /**
     * 获取当前请求的页码,空值返回默认页码1
     * @author: user
     * @param request
     * @return
     */
    protected int getCurrentPage() {
//        Map<String,Object> params=getParams(request);
//        if(null!=params){
//            String sCurrentPage=IbuStringUtil.valueOf(params.get("currentPage"));
//            if(StringUtils.isNotBlank(sCurrentPage)&& !sCurrentPage.equals("0")){
//                currentPage=Integer.parseInt(sCurrentPage);
//            }
//        }
        try {
			int page = 1;
			Enumeration paramNames = getRequest().getParameterNames();
			while (paramNames.hasMoreElements()) {
				String name = (String) paramNames.nextElement();
				if (name != null && name.startsWith("d-")
						&& name.endsWith("-p")) {
					String pageValue = getRequest().getParameter(name);
					if (pageValue != null) {
						try {
							page = Integer.parseInt(pageValue);
							break;
						} catch (Exception e) {
						}
					}
				}
			}
			setCurrentPage(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return currentPage;
    }
    
    
    public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
     * 获取当前请求的分页大小,空值返回默认分页大小15
     * @author: user
     * @param request
     * @return
     */
    protected int getPageSize(HttpServletRequest request) {
        Map<String,Object> params=getParams(request);
        if(null!=params){
            String sPageSize=IbuStringUtil.valueOf(params.get("pageSize"));
            if(StringUtils.isNotBlank(sPageSize) && !sPageSize.equals("0")){
                pageSize=Integer.parseInt(sPageSize);
            }
        }
        return pageSize;
    }
    /**
     * 获取请求的参数
     * @author: user
     * @param request
     * @return
     */
    protected Map<String,Object> getParams(HttpServletRequest request){
        String data=request.getParameter("data");
        if(null!=data){
            Map<String,Object> params=JSONUtil.StringToMap(data);
            return params;
        }
        return null;
    }

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	/**获取登录用户账户 */
	public ShiroUser getSessionUser(){
		//ShiroUser shiroUser = (ShiroUser)getRequest().getSession().getAttribute("loginInfo");
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getSession().getAttribute("loginInfo");
		if(null == shiroUser ){
			try {
				response.sendRedirect(getRequest().getContextPath()+"/logout.jsp");
				return null;
			} catch  (Exception e){
				e.printStackTrace();
			}
		}
		return shiroUser;
	}
    

}
