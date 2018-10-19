<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
	body {
    width: 100%;
    height: 100%;
    margin: 0;
    overflow: hidden;
    background-color: #FFFFFF;
    font-family: "Microsoft YaHei", sans-serif;
 }
 .pageSidebar{
    width: 240px;
    height:100%;
    padding-bottom: 30px;
    overflow: auto;
    background-color: #e3e3e3;
    font-size: 16px;
    text-align: left;
 }
 .splitter {
     width: 5px;
     height: 100%;
     bottom: 0;
     left: 240px;
     position: absolute;
     overflow: hidden;
     background-color: #fff;
 }
 .pageContent{
     height: 100%;
     min-width: 768px;
     left: 246px;
     top: 0;
     right: 0;
     z-index: 3;
     padding-bottom: 30px;
     position: absolute;
 }
 .pageContainer{
     bottom: 0;
     left:0;
     right: 0;
     top: 53px;
     overflow: auto;
     position: absolute;
     width: 100%;
 }
 .footer {
     width: 100%;
     height: 40px;
     line-height: 30px;
     margin-top: 0;
     left: 0;
     right: 0;
     bottom: 0;
     position: absolute;
     z-index: 10;
     background-color:#DFDFDF;
 }
</style>
<script type="text/javascript">
$(".nav li").click(function() {
    $(".active").removeClass('active');
    $(this).addClass("active");
});
</script>
</head>
<body>
	<!--顶部导航栏部分-->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" title="logoTitle" href="#">综合应用管理平台</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li role="presentation"><a href="#">当前用户：<span
							class="badge">${user.userName }</span></a></li>
					<li><a href="../login/logout"> <span
							class="glyphicon glyphicon-lock"></span>退出登录
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 中间主体内容部分 -->
	<div class="pageContainer">
		<!-- 左侧导航栏 -->
		<div class="pageSidebar">
			<!--  <ul class="nav nav-stacked nav-pills">
				<li role="presentation"><a href="view/FamilyInformation.html"
					target="mainFrame">导航链接1</a></li>
				<li role="presentation"><a href="nav2.html" target="mainFrame">导航链接2</a>
				</li>
				<li role="presentation"><a href="nav3.html" target="mainFrame">导航链接3</a>
				</li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#" > 导航链接4<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="nav1.html" target="mainFrame">导航链接4-1</a></li>
						<li><a href="nav2.html" target="mainFrame">导航链接4-2</a></li>
						<li><a href="nav3.html" target="mainFrame">导航链接4-3</a></li>
					</ul>
				</li>
				<li role="presentation"><a href="nav4.html" target="mainFrame">导航链接5</a>
				</li>
			</ul>-->
			<ul class="nav nav-stacked nav-pills">
				<c:if test="${not empty menuList}">
					<c:forEach items="${menuList}" var="menu" varStatus="i">
						<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" ><img src=" ${menu.icon}"/> ${menu.menuName}<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<c:forEach items="${menu.childList}" var="child" >
								<li><a href="${child.menuUrl }" target="mainFrame">${child.menuName }</a></li>
							</c:forEach>
						</ul>
						</li>
						
					</c:forEach>
				</c:if>
			</ul>
		</div>
		<!-- 左侧导航和正文内容的分隔线 -->
		<div class="splitter"></div>
		<!-- 正文内容部分 -->
		<div class="pageContent">
			<iframe src="" id="mainFrame" name="mainFrame"
				frameborder="0" width="100%" height="100%" frameBorder="0"></iframe>
		</div>
	</div>
	<!-- 底部页脚部分 -->
	<div class="footer">
		<p class="text-center">2017 &copy; 罗龙</p>
	</div>
</body>
</html>