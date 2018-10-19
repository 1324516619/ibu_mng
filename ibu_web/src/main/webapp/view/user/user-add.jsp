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
		var count=0;
		$("input[name='roleIds']").each(function(){
				if(this.checked==true){
					count = count+1;
				}
			}
		); 
		if(count<=0){
			alert("至少选择一个角色!");
			return false;
		}
		return true;
	}
	
	function add() {
		if (submitCheck()) {
			var roleArr = [];
			$("input[name='roleIds']").each(function(){
					if(this.checked==true){
						roleArr.push(this.value);
					}
				}
			);
			$('#roleArr').val(roleArr);
			
			$.ajax({
				type : "POST",
				async : false,
				dataType : "JSON",
				cache : false,
				url : "${ctx}/user/add.do",
				data : $("#serchForm").serialize(),
				success : function(data) {
					if (data.flag) {
						window.location.href="${ctx}/user/userList.do";
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
	<div id="MyProjectTitle"><span>${empty user.userId ? '新增':'修改'}用户</span></div>
		<form name="serchForm" id="serchForm"  method="post">
		<input type="hidden" name="roleArr" id="roleArr" value=""/>
		<input type="hidden" name="userId"  value="${user.userId}"/>
		<div id="AddContent">
			<div class="add_info_title">用户信息</div>
 		<table width="75%" align="center" border="0" cellspacing="6" cellpadding="0" id="add_project_info">
  			<tr>
  				<td width="200" align="right"><span><font color="#FF0000">*</font>用户名：</span></td>
  				<td><input name="userName"  value="${user.userName}" type="text" maxlength="16" /></td>
  				<td width="10%"></td>
  			</tr>
  			<tr>
  				<td width="200" align="right"><span><font color="#FF0000">*</font>密码：</span></td>
  				<td><input name="userPassword"  value="${user.userPassword}" type="password" maxlength="20" /></td>
  				<td width="10%"></td>
  			</tr>
  			<tr>
  				<td width="200" align="right"><span><font color="#FF0000">*</font>手&nbsp;&nbsp;&nbsp;机：</span></td>
  				<td><input name="phone"  value="${user.phone}" type="text" maxlength="11" /></td>
  				<td width="10%"></td>
  			</tr>
  			<tr>
  				<td width="200" align="right"><span><font color="#FF0000">*</font>昵&nbsp;&nbsp;&nbsp;称：</span></td>
  				<td><input name="nickName"  value="${user.nickName}" type="text" maxlength="50" /></td>
  				<td width="10%"></td>
  			</tr>
  			<tr>
  			   <td width="200" align="right"><span><font color="#FF0000">*</font>用户类型：</span></td>
  			   <td>
  			       <input name="type" value="1" type="radio" <c:if test="${user.type == '1' }">checked="checked"</c:if> />运营人员 
  			       <input name="type" value="2" type="radio" <c:if test="${user.type == '2' }">checked="checked"</c:if> />&nbsp;客户&nbsp;
  			       <input name="type" value="3" type="radio" <c:if test="${user.type == '3' }">checked="checked"</c:if> />&nbsp;销售人员&nbsp;
  			   </td>
  			   <td width="10%"></td>
  			</tr>
  			<tr> 
  				<td width="140" align="right"><span><font color="#FF0000">*</font>角&nbsp;&nbsp;&nbsp;色：</span></td>
  				<td>
  					<c:forEach items="${roleList}" var="role" varStatus="k">
  						 <input name="roleIds" value="${role.roleId }" type="checkbox" 
  						 <c:forEach items="${userRoleList}" var="ro" >
							<c:if test="${ro.roleId == role.roleId}">checked="checked"</c:if>
						 </c:forEach> 
  						 />${role.roleName}
  						 <c:if test="${0==k.index%4 }"></br></c:if>
  					</c:forEach>
			    </td>
			    <td width="10%"></td>
  			</tr>
   		</table>
		<div class="hr_20"></div>
		</div>
		<div class="MyProjectMgr_bnt borderTop">
			<input value="返回" onclick="window.history.back();" type="button" class="bnt_save"  style="border:0;cursor: pointer;" />
			<input type="button" class="bnt_save" style="border:0;cursor: pointer;" onclick="add();"  value="保存" />
		</div>
    </form>
</div>

</body>
</html>