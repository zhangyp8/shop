<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<title>注册成功</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container"
		style="width: 100%; height:300px; background: url('image/regist_bg.jpg');">
		<h2 align="center">注册成功，选择您的邮箱激活账号</h2>
		<div align="center">
			<span><a href="http://mail.163.com/">网易163邮箱</a></span>&nbsp;&nbsp;&nbsp;
			<span><a href="http://mail.126.com/">网易126邮箱</a></span>&nbsp;&nbsp;&nbsp;
			<span><a href="https://mail.qq.com/cgi-bin/loginpage">腾讯QQ邮箱</a></span>&nbsp;&nbsp;&nbsp;
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>