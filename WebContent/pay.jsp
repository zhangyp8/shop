<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在支付...</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		var second = 3;
		var timer = setInterval(function(){
			second--;
			if(second==0){
				clearInterval(timer)
				location.href = "${pageContext.request.contextPath }/myOrders";
			}
		}, 1000);
	})
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center" class="container"
		style="width: 100%; height:300px; background: url('image/regist_bg.jpg');">
		<h2 style="margin-top: 50px">支付成功，正在跳转...如不跳转，请<a href="${pageContext.request.contextPath }/myOrders" 
		style="text-decoration: none;">点击</a></h2>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>