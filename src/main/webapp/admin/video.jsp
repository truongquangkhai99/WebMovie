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
		<form method="post" class="user-form" enctype="multipart/form-data">
			<h2>VIDEO MANAGEMENT</h2>
			<c:if test="${!empty error}">
				<h3>${error}</h3>
			</c:if>
			<input type="text" placeholder="Id" name="id" value="${video == null ? '' :video.getId() }">
			<input type="text" placeholder="Title" name="title" value="${video == null ? '' : video.getTitle() }">
			<input type="file" placeholder="poster" name="poster" value="${video == null ? '' : video.getPoster() }">
			<input type="text" placeholder="views" name="views" value="${video == null ? '' : video.getViews() }">
			<input type="text" placeholder="Link" name="description" value="${video == null ? '' : video.getDescription() }">
			<div>
				<input type="radio" name="active" value="true" id="admin" ${video == null ? '' : video.isActive() ? 'checked' : '' }><label for="admin">Active</label>
				<input type="radio" name="active" value="false" id="user" ${video == null ? '' : video.isActive() ? '' : 'checked' }><label for="user">Not active</label>
			</div>
			<div>
				<button formaction="/asm/admin/video/create">Create</button>
				<button formaction="/asm/admin/video/update">Update</button>
				<button formaction="/asm/admin/video/delete">Delete</button>
				<button id="reset-button">Reset</button>
			</div>
		</form>
		<table class="table table-striped">
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Poster</th>
				<th>Views</th>
				<th>Link</th>
				<th>is Active</th>
			</tr>
			<c:forEach var="item" items="${videos }">
				<tr>
					<td>${item.getId() }</td>
					<td>${item.getTitle() }</td>
					<td>${item.getPoster() }</td>
					<td>${item.getViews() }</td>
					<td>${item.getDescription() }
					<td>${item.isActive() } <a href="video/edit?id=${item.getId() }" style="margin-inline: 10px" class="edit-user">Edit</a><a href="video/delete?id=${item.getId() }">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
		const resetButton = document.querySelector("#reset-button");
		const inputs = document.querySelectorAll(".user-form input[type='text']");
		const radios = document.querySelectorAll(".user-form input[type='radio']");
		resetButton.addEventListener("click", (e) => {
			e.preventDefault();
			inputs.forEach((value, index) => {
				value.value = ''
			})
			radios[0].removeAttribute("checked");
			radios[1].setAttribute("checked", true);
		});
	</script>
</body>
</html>