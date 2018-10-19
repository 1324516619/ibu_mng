<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body style="font-size: 16px">
<div class="nav">
<form id="serchForm" name="serchForm" action="${ctx }/order/orderList.do" method="post" onsubmit="">
	<div id="MyProjectTitle"><span>订单管理-->订单列表</span></div>
	<div id="MyProjectMgr" style="height: 60px;">
		<ul>
			<li class="w10">订单编号：</li>
			<li class="w20">
				<input name="orderNo" value="${order.orderNo }" type="text" maxlength="50" />
			</li>
			<li class="w10">创建时间：</li>
					<li class="w20">
					<input style="width: 44%" name="startTime" value="${order.startTime }" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						onkeydown="if(event.keyCode==8){return false;}" type="text"
						maxlength="20" class="Wdate" />- 
					<input style="width: 44%" name="endTime" value="${order.endTime }" readonly="readonly"onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						onkeydown="if(event.keyCode==8){return false;}" type="text"
						maxlength="20" class="Wdate" /></li>
	    </ul>
	</div>
	<div class="MyProjectMgr_bnt borderTop">
		<input class="bnt_save"   style="border:0;cursor: pointer;" type="submit" value="查询" />
	</div>
	<div id="sale_content0"  >
		<div class="sale" >
			<display:table id="obj1"  name="${page.list}" style="width:100%"  cellspacing="0" cellpadding="0"   requestURI="?" partialList="true"  defaultsort="0" 
		pagesize="${pageSize}" size="${totalSize}" export="false" keepStatus="false">
	 		<display:column title="" style="width:3%;TEXT-ALIGN: center">${obj1_rowNum}</display:column>
	 		<display:column style="height:35px !important;" title="订单ID">
	 			<c:out value="${obj1.id}"></c:out>
	 		</display:column>
	 		<display:column style="height:35px !important;" title="订单编号">
	 			<c:out value="${obj1.orderNo}"></c:out>
	 		</display:column>
	 		<display:column style="height:35px !important;" title="创建时间">
	 			<c:out value="${obj1.createDate}"></c:out>
	 		</display:column>
	 		<display:column style="height:35px !important;" title="订单金额">
	 			<c:out value="${obj1.amount}"></c:out>
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