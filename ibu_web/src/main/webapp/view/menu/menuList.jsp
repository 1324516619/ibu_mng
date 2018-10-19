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
		window.location.href="${ctx}/menu/toAdd.do";
	}
	
	function edit(menuId){
		window.location.href="${ctx}/menu/toAdd.do?menuId="+menuId;
	}
	
</script>
</head>
<body style="font-size: 16px">
<div class="nav">
<form id="serchForm" name="serchForm" action="${ctx }/menu/menuList.do" method="post" onsubmit="">
	<div id="MyProjectTitle"><span>系统管理-->菜单列表</span></div>
	<div id="MyProjectMgr" style="height: 60px;">
		<ul>
			<li class="w10">菜单名称：</li>
			<li class="w20">
				<input name="menuName" value="${menu.menuName }" type="text" maxlength="50" />
			</li>
	    </ul>
	</div>
	<div class="MyProjectMgr_bnt borderTop">
		<input class="bnt_save"   style="border:0;cursor: pointer;" type="submit" value="查询" />
		<ibu:hasPermission name="menuManage:add"> 
			<input class="bnt_save"   style="border:0;cursor: pointer;" type="button" value="新增" onclick="add()"/>
		</ibu:hasPermission>
	</div>
	<div id="sale_content0"  >
		<div class="sale" >
			<display:table id="obj1"  name="${page.list}" style="width:100%"  cellspacing="0" cellpadding="0"   requestURI="?" partialList="true"  defaultsort="0" 
		pagesize="${pageSize}" size="${totalSize}" export="false" keepStatus="false">
	 		<display:column title="" style="width:3%;TEXT-ALIGN: center">${obj1_rowNum}</display:column>
	 		<display:column style="height:35px !important;" title="菜单名称">
	 			<c:out value="${obj1.menuName}"></c:out>
	 		</display:column>
	 		<display:column style="height:35px !important;" title="菜单url">
	 			<c:out value="${obj1.menuUrl}"></c:out>
	 		</display:column>
	 		<display:column style="height:35px !important;" title="父菜单">
	 			<c:out value="${obj1.parentMenuName}"></c:out>
	 		</display:column>
	 		<display:column style="height:35px !important;" title="权限标识">
	 			<c:out value="${obj1.pageId}"></c:out>
	 		</display:column>
	 		<display:column style="width: 7%;TEXT-ALIGN: center" title="<div style='margin: 0 auto;TEXT-ALIGN: center;'>操作</div>">
	 			 <ibu:hasPermission name="menuManage:edit">  
	 			 	<a href="javascript:edit(${obj1.menuId})" >修改</a>&nbsp; 
	 			 </ibu:hasPermission>
	 			 <ibu:hasPermission name="menuManage:del">
	 			 	<a href="javascript:del(${obj1.menuId})" >删除</a>&nbsp;
	 			 </ibu:hasPermission>
			</display:column>
			<display:footer>
				<td colspan="11" style="height: 20px;">&nbsp;</td>
			</display:footer>
		</display:table>
			
	    </div>
    </div>
</form>
</div>
</body>

</html>