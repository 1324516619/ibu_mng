<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%
   try{
	   request.getSession().removeAttribute("loginInfo");
	   request.getSession().removeAttribute("loginName");
	   if(SecurityUtils.getSubject()!=null){
		   SecurityUtils.getSubject().logout();
	   }
   }catch(Exception ex){
	  
   }
%>
<c:redirect url="/view/login.jsp"/> 