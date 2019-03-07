<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册失败！</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">

	$(function(){
		var second = 5;
		var $time = $("#time");
		
		var timer = setInterval(function(){
			second--;
			time.innerHTML=second;
			if(second==0){
				clearInterval(timer)
				location.href = "${pageContext.request.contextPath }/register.jsp"
			}
		}, 1000);
	});

</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container"
		style="width: 100%; height:300px; background: url('image/regist_bg.jpg');">
	<div align="center">
		<h2>对不起注册失败<span id="time" style="color: red">5</span>秒后跳转</h2>
	</div>
	
</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>