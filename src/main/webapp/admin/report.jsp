<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="/asm/admin/">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Shizuru:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');
	.user-management-wrapper{
		height: 100%;
		width: 100%;
	}
	.user-form{
		width: 370px;
		padding: 15px 0;
		border: 1px solid black;
		display: flex;
		flex-direction: column;
		gap: 22px;
		justify-content: flex-start;
		align-items: center;
		font-family: "times new roman";
		margin: 10px auto;
	}
	.user-form button{
		font-weight: bold;
	}
	.user-form input[type="text"]{
		width: 80%;
		padding: 5px 3px;
		font-size: 1.1em;
		border-radius: 5px;
		font-weight: bold;
	}
	.user-form input[type="text"]:first-child {
		margin-top: 4px;
	}
	.user-form input[type="text"]::placeholder{
		text-align: center;
		font-weight: bold;
	}
	.user-form h2{
		text-align: center;
		width: 100%;
		border-bottom: 1px solid #555;
		font-weight: bold;
		padding-bottom: 4px;
	}
	.user-form label{
		font-weight: bold;
	}
	.table-striped{
		margin-top: 12px;
	}
</style>
<title>page.getTitle()</title>
</head>
<body>	
	<div class="user-management-wrapper">
		<table class="table table-striped">
			<tr>
				<th>Video id</th>
				<th>Video title</th>
				<th>${critial}</th>
			</tr>
			<c:forEach var="item" items="${list }">
				<tr>
					<td>${item[0]}</td>
					<td>${item[1]}</td>
					<td>${item[2]}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>