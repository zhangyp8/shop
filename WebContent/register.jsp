<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
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
.error{
	color: red;
}
</style>
<script type="text/javascript">
	function check(img) {
		img.src = "${pageContext.request.contextPath }/checkImg?time="
				+ new Date().getTime();
	}
	
	/* 异步校验用户名是否存在 */
	/* $(function() {

		$("#username").blur(function() {
			var usernameInput = $(this).val();
			/* alert(usernameInput); */
			/*$.post("${pageContext.request.contextPath }/checkUsername", {
				"username" : usernameInput
			}, function(data) {
				var isExist = data.isExist;
				var usernameInfo = "";
				if (isExist) {
					usernameInfo = "该用户名已存在";
					$("#usernameInfo").css("color", "red");
				} else {
					usernameInfo = "该用户名可用";
					$("#usernameInfo").css("color", "green");
				}
				$("#usernameInfo").html(usernameInfo);
			}, "json");

		});
	}); */

/* 	$(function() {
		$("#confirmpwd").blur(function() {
			var pwd = $("#inputPassword3").val();
			var confirmpwd = $("#confirmpwd").val();
			var pwdInfo = "";
			if (pwd != confirmpwd) {
				pwdInfo = "两次输入密码不一致！"
				$("#passwordInfo").css("color", "red");
				//$("#submit").prop("disabled")
			}
			$("#passwordInfo").html(pwdInfo);
		});
	}); */
	$.validator.addMethod(
		//自定义校验规则的名称
		"checkUsername",
		//校验函数
		function(value,element,params){
			//value:输入的内容
			//element:被校验的元素对象
			//params：规则对应的参数值
			//目的：对输入的username进行ajax校验
			var flag = true;
			$.ajax({
				"async":false,
				"url":"${pageContext.request.contextPath}/checkUsername",
				"data":{"username":value},
				"type":"POST",
				"dataType":"json",
				"success":function(data){
					flag = data.isExist;
				}
			});
			
			
			//返回false代表该校验器不通过
			return !flag;
		
		}
	);
	$.validator.addMethod(
			//自定义校验规则的名称
			"checkCode",
			//校验函数
			function(value,element,params){
				//value:输入的内容
				//element:被校验的元素对象
				//params：规则对应的参数值
				//目的：对输入的验证码进行ajax校验
				var flag = true;
				$.ajax({
					"async":false,
					"url":"${pageContext.request.contextPath}/checkCode",
					"data":{"code":value},
					"type":"POST",
					"dataType":"json",
					"success":function(data){
						flag = data.isRight;
					}
				});
				
				
				//返回false代表该校验器不通过
				return flag;
			
			}
		);
	$(function() {
		$("#registerForm").validate({
			rules:{
				"username":{
					"required":true,
					"checkUsername":true
				},
				"password":{
					"required":true,
					"rangelength":[6,12]
				},
				"confirmpwd":{
					"required":true,
					"rangelength":[6,12],
					"equalTo":"#password"
				},
				"email":{
					"required":true,
					"email":true
				},
				"code":{
					"checkCode":true
				}
			},
			messages:{
				"username":{
					"required":"用户名不能为空",
					"checkUsername":"该用户已存在"
				},
				"password":{
					"required":"密码不能为空",
					"rangelength":"密码长度为6-12位"
				},
				"confirmpwd":{
					"required":"密码不能为空",
					"rangelength":"密码长度6-12位",
					"equalTo":"两次密码不一致"
				},
				"email":{
					"required":"邮箱不能为空",
					"email":"邮箱格式不正确"
				},
				"code":{
					"checkCode":"验证码不正确"
				}
			}
		});
	});

</script>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form class="form-horizontal" id="registerForm" style="margin-top: 5px;" method="post" 
					action="${pageContext.request.contextPath }/register">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="请输入用户名">
						</div>
						<div class="col-sm-3">
							<span id="usernameInfo"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd"
								name="confirmpwd" placeholder="请输入确认密码">
						</div>
						<div class="col-sm-3">
							<span id="passwordInfo"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="email" name="email" placeholder="请输入有效的邮箱地址">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption"
								name="name" placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">联系电话</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="telephone"
								name="telephone" placeholder="请输入电话号码">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio1" value="男"> 男
							</label> <label class="radio-inline"> <input type="radio"
								name="sex" id="inlineRadio2" value="女"> 女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name="birthday">
						</div>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="code">

						</div>
						<div class="col-sm-2">
							<img src="${pageContext.request.contextPath }/checkImg"
								onclick="check(this)" id="code" name="code" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-3" style="color: red">${codeInfo }</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								id="submit"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>




