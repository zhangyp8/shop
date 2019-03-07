<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->

<script type="text/javascript">
	function exit() {
		if(confirm("您确定要退出吗？")){
			location.href="${pageContext.request.contextPath}/exit";
		}
	}
</script>
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/Pshop.PNG" style="width: 50%"/>
	</div>
	<div class="col-md-5" style="padding-top:20px">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:40px" >
		<ol class="list-inline">
		<c:if test="${empty user }">
			<li><a href="login.jsp">登录</a></li>
			<li><a href="register.jsp">注册</a></li>
		</c:if>
		<c:if test="${!empty user }">
			<li><span style="color: #1979CA">欢迎您，${user.username }</span></li>
			<li><a href="javaScript:;" onclick="exit()">退出</a></li>
		</c:if>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath}/myOrders">我的订单</a></li>
		</ol>
	</div>
</div>
	<script type="text/javascript">
		$(function(){
			var content = "";
			$.post(
				"${pageContext.request.contextPath }/findCaotgry",
				function (data) {
					//[{"cid":"xxx","cname":"xxxx"},{},{}]
					//动态创建<li><a href="#">${category.cname }</a></li>
					for(var i = 0;i<data.length;i++){
						content += "<li><a href='${pageContext.request.contextPath }/productList?cid="+data[i].cid+"'>"+data[i].cname+"</a></li>"; 
						$("#catogryUl").html(content);
					}
				},
				"json"
			);
		});
	</script>
<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath }/default.jsp">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="catogryUl">
					
				</ul>
				<form class="navbar-form navbar-right" role="search" method="post" action="${pageContext.request.contextPath }/searchProduct">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search" name="search">
					</div>
					<button type="submit" class="btn btn-default">查询商品</button>
				</form>
			</div>
		</div>
	</nav>
</div>