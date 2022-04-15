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
	.register-wrapper{
		width: 400px;
		padding-bottom: 20px;
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
	input#input-register{
		display: block;
		margin: auto;
		padding-left: 5px;
	}
	input#input-register::placeholder{
		font-weight: bold;
		text-align: center;
	}
	button#register-button{
		display: block;
		margin: auto;
		padding: 2px 6px;
		border-radius: 6px;
		font-weight: bold;
	}
	.register-wrapper > h3{
		text-align: center;
	}
</style>
<title>${page.getTitle() }</title>
</head>
<body>
	<div class="register-wrapper">
		<h1>Registration</h1><hr>
		<c:if test="${!empty error }">
			<h3>${error }</h3>
		</c:if>
		<form action="/asm/register" method="post">
			<input type="text" name="id" placeholder="User Name" id="input-register"><br/>
			<input type="password" name="password" placeholder="Password" id="input-register"><br/>
			<input type="password" name="password2" placeholder="Confirm Password" id="input-register"><br/>
			<input type="text" name="email" placeholder="Email" id="input-register"><br/>
			<input type="text" name="fullname" placeholder="Full Name" id="input-register"><br/>
			<button id="register-button">Registration</button>
		</form>
	</div>
</body>
</html>