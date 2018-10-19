<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单新增</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	function submitCheck(){
		return true;
	}
	function add() {
		if (submitCheck()) {
			$.ajax({
				type : "POST",
				async : false,
				dataType : "JSON",
				cache : false,
				url : "${ctx}/menu/add.do",
				data : $("#serchForm").serialize(),
				success : function(data) {
					if (data.flag) {
						window.location.href="${ctx}/menu/menuList.do";
					} else {
						alert(data.msg);
					}
				}
			});
		}
	}
 
</script>


</head>
<body>
<div id="right_content">
	<div id="MyProjectTitle"><span>${0==menu.menuId ? '新增':'编辑' }菜单</span></div>
		<form name="serchForm" id="serchForm"  method="post">
		<div id="AddContent">
			<input type="hidden" name="menuId" value="${menu.menuId}"/>
			<table width="98%" style="height: 30%" align="center" border="0" cellspacing="6" cellpadding="0" id="add_project_info">
			<tr>
				<td width="200" align="right"><span><font color="#FF0000">*</font>菜单名称：</span></td>
				<td ><input name="menuName"  value="${menu.menuName}" type="text"  type="text" maxlength="24" /></td>
				<td width="40%"></td>
			</tr>
			<tr>
				<td width="200" align="right"><span>父菜单：</span></td>
				<td >
					<select name="parentMenuId" >
							<option value="0">无</option>
							<c:if test="${not empty menuList}">
						 	<c:forEach items="${menuList}" var="me" >
						 		<option value="${me.menuId}" <c:if test="${ menu.parentMenuId == me.menuId}">selected="selected"</c:if> >${me.menuName}</option>
						 		<c:if test="${not empty me.childList}">
						 			<c:forEach items="${me.childList}" var="childMenu" >
						 				<option value="${childMenu.menuId}" <c:if test="${ menu.parentMenuId == childMenu.menuId}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${childMenu.menuName}</option>
						 			</c:forEach>
						 		</c:if>
							</c:forEach>
						</c:if>
		            </select>
				</td>
				<td width="40%"></td>
			</tr>
			<tr>
				<td width="200" align="right"><span><font color="#FF0000">*</font>菜单url地址：</span></td>
				<td ><input name="menuUrl"  value="${menu.menuUrl}" type="text"  type="text" maxlength="200" /></td>
				<td width="40%"></td>
			</tr>
			<tr>
				<td width="200" align="right"><span>菜单icon：</span></td>
				<td ><input name="icon"  value="${menu.icon}" type="text"  type="text" maxlength="200" /></td>
				<td width="40%"></td>
			</tr>
			<tr>
				<td width="200" align="right"><span><font color="#FF0000">*</font>菜单编码：</span></td>
				<td ><input name="menuCode"  value="${menu.menuCode}" type="text"  type="text" maxlength="20" /></td>
				<td width="40%"></td>
			</tr>
			<tr>
				<td width="200" align="right"><span><font color="#FF0000">*</font>权限标识：</span></td>
				<td >
					<input name="pageId"  value="${menu.pageId}" type="text" maxlength="20" >
					
				</td>
				<td width="20%"><font color="#FF0000">(用于关联查询对应的权限)</font></td>
			</tr>
			</table>
		</div>
		
		<div class="MyProjectMgr_bnt borderTop">
			<input value="返回" onclick="window.history.back();" type="button" class="bnt_save"  style="border:0;cursor: pointer;" />
			<input type="button" class="bnt_save" style="border:0;cursor: pointer;" onclick="add();"  value="保存" />
		</div>
    </form>
</div>

</body>
</html>