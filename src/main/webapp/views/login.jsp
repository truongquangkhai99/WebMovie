<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Roboto Mono:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
	.login_wrapper{
		width: 400px;
		height: 300px;
		margin: 20px auto;
		font-family: 'Roboto Mono';
		border-radius: 10px;
		box-shadow: 5px 8px 10px #333,
		8px 12px 12px #888,
		inset -5px -8px 10px #333,
		-5px -8px 10px #333,
		-8px -12px 12px #888,
		0 0 12px #555,
		6px 0 12px #555,
		0 4px 12px #555,
		inset 5px 8px 10px #aaa,
		inset 7px 12px 12px #ccc; 
	}
	h1{
		text-align: center;
	}
	input#input-login{
		display: block;
		margin: auto;
		padding-left: 5px;
	}
	input#input-login::placeholder{
		font-weight: bold;
		text-align: center;
	}
	button#login-button{
		display: block;
		margin: auto;
		padding: 2px 6px;
		border-radius: 6px;
		font-weight: bold;
	}
	.error-message{
		color: red;
		width: 100%;
		text-align: center;
		background-color: rgba(255, 0, 0, 0.4);
		border: 1px solid rgba(255, 0, 0, 0.8);
	}
</style>
<title>${page.getTitle() }</title>
</head>
<body>
	<div class="login_wrapper">
		<h1>Login</h1><hr>
		<c:if test="${error != null}">
			<h3 class="error-message">Account is not exist</h3>
		</c:if>
		<form action="/asm/login" method="post">
			<input type="text" name="id" placeholder="User Name" id="input-login"><br/>
			<input type="password" name="password" placeholder="Password" id="input-login"><br/>
			<button id="login-button">Login</button>
		</form>
	</div>
</body>
</html>