<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>购物车</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="css/style.css" type="text/css" />
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
		
		<script type="text/javascript">
			function deleteProFromCar(pid) {
				if (confirm("您是否删除该项？")) {
					location.href="${pageContext.request.contextPath}/deleProFromCar?pid="+pid;
				}
			}
			function clearCar() {
				if (confirm("您是否要清空购物车？")) {
					location.href="${pageContext.request.contextPath}/clearCar";
				}
			}
		
		</script>
		
	</head>

	<body>
		<!-- 引入header.jsp -->
		<jsp:include page="/header.jsp"></jsp:include>
		<c:if test="${empty car }">
			<div align="center">
				<span style="text-align: center;">对不起，您的订单为空！</span><br>
				<c:if test="${empty user }">
				<a href="login.jsp" style="text-decoration: none;text-align: center;">登录</a><br>
				<a style="text-decoration: none;text-align: center;" href="${pageContext.request.contextPath }/default.jsp">去购物</a>
				</c:if>
				<c:if test="${!empty user }">
				<a style="text-decoration: none;text-align: center;" href="${pageContext.request.contextPath }/default.jsp">去购物</a>
				</c:if>
			</div>
		</c:if>
		<c:if test="${!empty car }">
		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">订单详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							<c:forEach items="${car.carItems }" var="entry">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath }/${entry.value.product.pimage }" width="70" height="60">
								</td>
								<td width="30%">
									<a href="${pageContext.request.contextPath }/productInfo?pid=${entry.value.product.pid }">${entry.value.product.pname }</a>
								</td>
								<td width="20%">
									￥${entry.value.product.shop_price }
								</td>
								<td width="10%">
									${entry.value.buyNum }
								</td>
								<td width="15%">
									<span class="subtotal">￥${entry.value.subTotal }</span>
								</td>
								<td>
									<a href="javascript:void(0);" class="delete" onclick="deleteProFromCar(${entry.value.product.pid })">删除</a>
								</td>
							</tr>
							
							</c:forEach>
							
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					<em style="color:#ff6600;">
				登录后确认是否享有优惠&nbsp;&nbsp;
			</em> 赠送积分: <em style="color:#ff6600;">${car.totalPrice }</em>&nbsp; 商品金额: <strong style="color:#ff6600;">￥${car.totalPrice }元</strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="javaScript:;" id="clear" class="clear" onclick="clearCar()">清空购物车</a>
					<a href="${pageContext.request.contextPath }/addOrder">
						<input type="button" width="100" value="提交订单" name="button"  style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</a>
				</div>
			</div>

		</div>
		</c:if>
		<!-- 引入footer.jsp -->
		<jsp:include page="/footer.jsp"></jsp:include>

	</body>

</html>