<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
function submitCheck(){
	return true;
}
	//下载
function open2DownLoad(id){
	if(window.confirm("确定是否下载该文件?")){
		//每一行下载次数的jquery对象
		var downJQ = $('#'+id+'').parent().parent().find('td').eq(6);
 		var value = parseInt(downJQ.html())+1;
 		downJQ.html(value);
		window.location.href="${ctx}/exportdata/downExportdata.do?reportSelectId="+id;
   }
}
	

</script>
</head>
<body>
<form id="serchForm" name="serchForm" action="${ctx }/exportdata/list.do" enctype="multipart/form-data" method="post" onsubmit="">
	<div id="MyProjectTitle"><span>报表导出</span></div>
	<div id="MyProjectMgr" style="height: 60px;">
		<ul>
			<li class="w10">文件名：</li>
			<li class="w20">
			 	<input name="exportDataVo.fileName" value="${exportDataVo.fileName}" type="text" maxlength="20" />
			</li>
			<li class="w10">创建时间： </li><li ><input style="width:80px" name="startTime" id="startDate" readonly="readonly" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onkeydown="if(event.keyCode==8){return false;}" value="${exportDataVo.startTime}" type="text" maxlength="20" size="20"/></li>
			<li>-</li><li><input style="width:80px" name="endTime" id="endDate" readonly="readonly" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onkeydown="if(event.keyCode==8){return false;}" value="${exportDataVo.endTime}" type="text" maxlength="20" size="20"/></li>
			<li class="w10"></li><li class="w20"></li>
	    </ul>
	</div>
	<div class="MyProjectMgr_bnt borderTop">
		<label style="color:red;height: 32px;line-height: 32px;">&nbsp;&nbsp;注意：如果Microsoft Office不能打开，请安装<a href="http://rj.baidu.com/soft/detail/14391.html?ald" target="_blank">WPS Office</a></label>
		<input class="bnt_save"   style="border:0;cursor: pointer;" type="submit" value="查询" />
	</div>
	<div id="sale_content0">
		<div class="sale" >
			<display:table id="obj1"  name="${page.list}" style="width:100%"  cellspacing="0" cellpadding="0"   requestURI="?" partialList="true"  defaultsort="0" 
				pagesize="${pageSize}" size="${totalSize}" export="false" keepStatus="false">
		 		<display:column  style="width: 5%;TEXT-ALIGN: center;" title="${checkAll}">
				<input type="checkbox" name="reportSelectId" id="reportSelectId" value="${obj1.batchNo}" />
			</display:column>
			<display:column title="批号" property="batchNo">
	 		</display:column>
	 		<display:column title="文件名称">
	 			<ibu:tooTip value="${obj1.fileName}" cut="25" ></ibu:tooTip>
	 		</display:column>
	 		<display:column title="文件大小">
	 		   <c:if test="${!empty obj1.fileSize}">
	 		       <ibu:doubleValue doubleValue="${(obj1.fileSize/1024)/1024 }" />MB
	 		   </c:if>
	 		</display:column>
	 		<display:column title="处理进度">
	 			 <c:choose>
	 			    <c:when test="${obj1.reportProcess == 1}">
	 			    	待处理
	 			    </c:when>
	 			    <c:when test="${obj1.reportProcess == 2}">
	 			    	处理中
	 			    </c:when>
	 			    <c:when test="${obj1.reportProcess == 3}">
	 			   		 已处理
	 			    </c:when>
	 			 </c:choose>
	 		</display:column>
	 		<display:column title="处理结果">
	 		     <c:choose>
	 		        <c:when test="${obj1.reportState == 1}">
	 		                              成功
	 		        </c:when>
	 		        <c:when test="${obj1.reportState == 2}">
	 		        	失败
	 		        </c:when>
	 		        <c:when test="${obj1.reportState == 3}">
	 		        	异常
	 		        </c:when>
	 		     </c:choose>
	 		</display:column>
	 		<display:column  title="下载次数" sortable="false">
	 				<c:out value="${obj1.downloadTimes}"></c:out>
	 		</display:column>
	 		<display:column  title="创建时间" sortable="false">
	 			 <c:if test="${not empty obj1.createdDate}">
	 				<fmt:formatDate value="${obj1.createdDate}" pattern="yyyy-MM-dd HH:mm" type="date" />
	 			</c:if>
	 		</display:column>
	 		<display:column  title="更新时间" sortable="false">
	 			 <c:if test="${not empty obj1.updatedDate}">
	 				<fmt:formatDate value="${obj1.updatedDate}" pattern="yyyy-MM-dd HH:mm" type="date" />
	 			</c:if>
	 		</display:column>
	 		<display:column  title="申请人" sortable="false">
	 		 		<c:out value="${obj1.createdName}"></c:out>
	 		</display:column>
	 		<display:column style="width: 12%;TEXT-ALIGN: center" title="<div style='margin: 0 auto;TEXT-ALIGN: center;'>操作</div>">
	 				<c:if test="${obj1.reportState eq 1 && obj1.reportProcess eq '3'}">
		 				<a href="javascript:open2DownLoad('${obj1.batchNo}')" id="${obj1.batchNo}">下载</a>
		 			</c:if>
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