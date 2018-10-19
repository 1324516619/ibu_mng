<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	function add(){
		window.location.href ='${ctx}/user/toAdd.do';
	}
	
	function edit(userId){
		window.location.href ='${ctx}/user/toAdd.do?userId='+userId;
	}
	
	function toExport(){
 		if(!confirm("确认导出吗？")){
 			return;
 		}
		$("#serchForm").attr("action","${ctx }/user/toExport.do");
		$("#serchForm").submit();
    }
	

</script>
</head>
<body>
<form id="serchForm" name="serchForm" action="${ctx }/user/userList.do" method="post" onsubmit="">
	<div id="MyProjectTitle"><span>系统管理-->用户列表</span></div>
	<div id="MyProjectMgr" style="height: 60px;">
		<ul>
			<li class="w10">用户名：</li>
			<li class="w20">
				<input name="userName" value="${user.userName }" type="text" maxlength="50" />
			</li>
			<li class="w10">手机号：</li>
			<li class="w20">
				<input name="phone" value="${user.phone }" type="text" maxlength="50" />
			</li>
	    </ul>
	</div>
	<div class="MyProjectMgr_bnt borderTop">
		<input class="bnt_save"   style="border:0;cursor: pointer;" type="submit" value="查询" />
		<ibu:hasPermission name="userManage:add"> 
			<input class="bnt_save"   style="border:0;cursor: pointer;" type="button" onclick="add()" value="新增" />
		</ibu:hasPermission>
		<input class="bnt_save"   style="border:0;cursor: pointer;" type="button" onclick="toExport()" value="导出" />
		
	</div>
	<div id="sale_content0">
		<div class="sale" >
			<display:table id="obj1"  name="${page.list}" style="width:100%"  cellspacing="0" cellpadding="0"   requestURI="?" partialList="true"  defaultsort="0" 
				pagesize="${pageSize}" size="${totalSize}" export="false" keepStatus="false">
		 		<display:column title="" style="width:3%;TEXT-ALIGN: center">${obj1_rowNum}</display:column>
		 		<display:column style="height:35px !important;" title="用户名">
		 			<c:out value="${obj1.userName}"></c:out>
		 		</display:column>
		 		<display:column style="height:35px !important;" title="用户昵称">
		 			<c:out value="${obj1.nickName}"></c:out>
		 		</display:column>
		 		<display:column style="height:35px !important;" title="手机">
		 			<c:out value="${obj1.phone}"></c:out>
		 		</display:column>
		 		<display:column style="height:35px !important;" title="创建时间">
		 			<c:out value="${obj1.createDate}"></c:out>
		 		</display:column>
		 		<display:column style="height:35px !important;" title="用户类型">
		 			<c:if test="${obj1.type eq 1 }">运营人员  </c:if>
		 			<c:if test="${obj1.type eq 2 }">客户</c:if>
		 			<c:if test="${obj1.type eq 3 }">销售  </c:if>
		 		</display:column>
		 		<display:column style="width: 7%;TEXT-ALIGN: center" title="<div style='margin: 0 auto;TEXT-ALIGN: center;'>操作</div>">
		 			 <ibu:hasPermission name="userManage:edit"> 
		 			 	<a href="javascript:edit(${obj1.userId})" >修改</a>&nbsp;
		 			 </ibu:hasPermission>
		 			 <ibu:hasPermission name="userManage:del">
		 				<a href="javascript:del(${obj1.userId})" >删除</a>&nbsp;
		 			 </ibu:hasPermission>
				</display:column>
				<display:footer>
					<td colspan="11" style="height: 20px;">&nbsp;</td>
				</display:footer>
			</display:table>
	    </div>
    </div>
</form>
</body>

</html>