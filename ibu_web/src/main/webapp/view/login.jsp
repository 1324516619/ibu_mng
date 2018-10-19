<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<link href="${ctx}/css/main.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}/js/jQuery.js"></script>
	<script type="text/javascript" src="${ctx}/js/fun.base.js"></script>
	<script type="text/javascript" src="${ctx}/js/script.js"></script>
	<script type="text/javascript">
		function getVimg(){
			document.getElementById("imgCode").src = "${ctx}/kaptcha/kaptcha.jsp?timeamp=" + new Date().getTime(); 
		}
	</script>
	<style type="text/css">
		.text1{
		    padding: 5px;
		    background-color: #FCFCFC;
		    line-height: 14px;
		    width: 110px;
		    font-size: 12px;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    border-radius: 4px;
		    -webkit-box-shadow: #CCC 0px 0px 5px;
		    -moz-box-shadow: #CCC 0px 0px 5px;
		    box-shadow: #CCC 0px 0px 5px;
		    border: 1px solid #CCC;
		    font-size: 12px;
		    margin-top: 10px; 
    }
	</style>
</head>
<body>
<h2 style="text-align:center">欢迎</h2>
	<form action="${ctx}/login.do" method="post">
		<div class="login">
		    <div class="box png">
				<div class="logo png"></div>
				<div class="input">
					<div class="log">
						<div class="name">
							<input type="text" class="text" id="value_1" placeholder="用户名" name="userName" tabindex="1">
						</div>
						<div class="pwd">
							<input type="password" class="text" id="value_2" placeholder="密码" name="userPassword" tabindex="2">
							<font style="color: red">${isok}</font>
							</br><input type="text" id="inputRand" class="text1" name="inputRand" placeholder="图形验证码" autocomplete="off">
								<div style="margin-left: 137px; margin-top: -30px;"><img style="width: 100px" src="${ctx}/kaptcha/kaptcha.jsp" id="imgCode" onclick="getVimg();" name ="imgCode" />
								</div>
							<input type="submit" class="submit" tabindex="3" value="登录">
							<div class="check"></div>
						</div>
						<div class="tip"></div>
					</div>
				</div>
			</div>
		    <div class="air-balloon ab-1 png"></div>
			<div class="air-balloon ab-2 png"></div>
		    <div class="footer"></div>
		</div>
	</form>

</body>
</html>