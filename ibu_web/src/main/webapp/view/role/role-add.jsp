<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
function  checkAll_Role(obj,trid){
	if($(obj).prop("checked")==true){
			if($("[id='"+trid+"']").length <=1 ){
				$("[id='"+trid+"']").find("input").each(function(i){
					$(this).prop("checked",true);
				});
			}else {
				$("[id='"+trid+"']").each(function(i){
					$(this).find("input").each(function(j){
						$(this).prop("checked",true);
					});					
				});
			}
	}else {
		 
		 if($("[id='"+trid+"']").length <=1 ){
				$("[id='"+trid+"']").find("input").each(function(i){
					$(this).prop("checked",false);
				});
			}else {
				$("[id='"+trid+"']").each(function(i){
					$(this).find("input").each(function(j){
						$(this).prop("checked",false);
					});					
				});
			}
			
	}
	
	
}

function submitCheck(){
	var count=0;
	$("input[name='menuSelectIds']").each(function(){
			if(this.checked==true){
				count = count+1;
			}
		}
	); 
	if(count<=0){
		alert("至少选择一个菜单权限!");
		return false;
	}
	return true;
}

function add(){
	if(submitCheck()){
		var menuArr = [];
		var permiArr = [];
		$("input[name='menuSelectIds']").each(function(){
				if(this.checked==true){
					menuArr.push(this.value);
				}
			}
		);
		$("input[name='permiSelectIds']").each(function(){
				if(this.checked==true){
					permiArr.push(this.value);
				}
			}
		);
		
		$('#menuArr').val(menuArr);
		$('#permiArr').val(permiArr);
		
		$.ajax({
			type : "POST",
			async : false,
			dataType : "JSON",
			cache : false,
			url : "${ctx}/role/add.do",
			data : $("#serchForm").serialize(),
			success : function(data) {
				if (data.flag) {
					window.location.href="${ctx}/role/roleList.do";
				} else {
					alert(data.msg);
				}
			}
		});
	}
}
	
</script>
</head>
<body style="font-size: 16px">
	<div id="right_content">
<div id="MyProjectTitle"><span>${0==role.roleId ? '修改':'新增'}角色</span></div>
<form id="serchForm" name="serchForm" style="margin-top: 4px;" method="post">
<input type="hidden" id="menuArr" name="menuArr" value=""/>
<input type="hidden" id="permiArr" name="permiArr" value=""/>
<input type="hidden" id="roleId" name="roleId" value="${role.roleId}"/>

<DIV id="AddContent">

	<table width="98%" align="center" border="0" cellspacing="6" cellpadding="0" id="add_project_info">
	<tr>
		<td width="200" align="right"><span><font color="#FF0000">*</font>角色名称：</span></td>
		<td ><input style="width:60%" name="roleName"  value="${role.roleName}"  type="text" maxlength="16" /></td>
		<td width="60%">&nbsp;</td>
	</tr>
	<tbody id="role_menu_tr">
		<tr>
			<td width="200" align="right"></td>
			<td>
				<c:if test="${not empty menuList}" >
					<c:forEach items="${menuList}" var="sysMenu" varStatus="iii" >
						<tr>
		 	 			 	<td align="left" width="30%"></td>
							<td align="left" width="70%">
								<div align="left">
								<font color='#000'><strong>${sysMenu.menuName} </strong></font>
									<input  style="height:24;vertical-align: middle;" type="checkbox" name="menuSelectIds" 
									<c:forEach items="${ role.menuIdList}" var="roMenu" >
										<c:if test="${roMenu == sysMenu.menuId}">checked="checked"</c:if>
									</c:forEach> 
									value="${sysMenu.menuId }" onclick="checkAll_Role(this,'parentMenu__${iii.index }')" >
								</div>
						  	</td>
						</tr>
						<c:if test="${not empty sysMenu.childList}">
							<c:forEach items="${sysMenu.childList}" var="kk" varStatus="kkk">
								<tr id="parentMenu__${iii.index}">
									<td align="left"  width="30%"></td>
									<td align="left"  width="70%">
										<div align="left">
											<input name="menuSelectIds" style="height:24;vertical-align: middle;" 
											<c:forEach items="${ role.menuIdList}" var="roMenu" >
												<c:if test="${roMenu == kk.menuId}">checked="checked"</c:if>
											</c:forEach> 
											type="checkbox" value="${kk.menuId}" />
											${kk.menuName}:
											<c:forEach items="${permiList}" var="k" varStatus="kIndex" >
												<c:if test="${kk.pageId == k.pageId }">
												<input name="permiSelectIds" style="height:24;vertical-align: middle;" type="checkbox"
												<c:forEach items="${role.permiList}" var="permi" >
													<c:if test="${permi == k.permiValue}">checked="checked"</c:if>
												</c:forEach>
													value='${k.permiValue}'/>${k.permiDesc}
												</c:if>
						 					</c:forEach>
										</div>
									</td>
								</tr>
								
							</c:forEach>
							
						</c:if>
					</c:forEach>
				</c:if>
			</td>
			<td width="10%"></td>
		
		</tr>
	</tbody>
	</table>
	<div class="hr_20"></div>
</DIV>
<div class="MyProjectMgr_bnt borderTop">
	<input value="返回" onclick="window.history.back()" type="button" class="bnt_save"  style="border:0;cursor: pointer;" />
	<input type="button" class="bnt_save" style="border:0;cursor: pointer;" onclick="add();" value="保存" />
</div>
</form>
<br />
 
</div>
	
</body>

</html>