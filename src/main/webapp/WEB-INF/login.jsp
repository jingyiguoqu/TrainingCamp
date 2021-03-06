<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>登录</title>
		<link rel="stylesheet" href="css/login.css" />
		<script type="text/javascript" src="js/login.js"></script>
	</head>

	<body id="wb">
		<header></header>
		<div id="login">
			<p id=sys_title_C>训练营订座系统</p>
			<p id=sys_title_E>TEDU OF ZHUHAI GROUP</p>
			<img src="img/login/admin_ico.png" class="admin_ico" /><br />
			<img src="img/login/password_ico.png" class="pwd_ico" />
			<form action="login.do" method="post">
				<input type="text" id="username" value="请输入用户名" name="username"
					onfocus="javascript:this.value = '';"
					onblur="javascript:if(this.value == null || this.value == ''){this.value = '请输入用户名'}"/>
				<span id="user_message">${login_failed}</span><br />
				<input type="text" id="pwd_info" value="请输入密码" 
					onfocus="javascript:this.style.display = 'none';
					document.getElementById('password').style.display = 'inline';
					document.getElementById('password').focus();
					"/>
				<input type="password" id="password" name="password"
					onblur="javascript:if(this.value == null || this.value == ''){this.style.display = 'none';
						document.getElementById('pwd_info').style.display = 'inline';
					}"/>
				<span id="password_message">${pwd_failed}</span><br />
				<input id="login_do" type="submit" value="登录"/> <br />
				<button type="button" id="miss_pwd" 
					onclick="javascript:alert('请联系教学主管彭山莉老师')" >
					忘记密码?
				</button>
			</form>
		</div>
		<footer>Welcome To The Tarena</footer>
	</body>

</html>