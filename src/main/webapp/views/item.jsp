<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ page import="com.asm.model.*"%>
<%@ page import="java.util.List"%>
<%
Users user = (Users) request.getSession().getAttribute("user");
Boolean admin = (user == null ? false : user.getAdmin());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
.item-wapper {
	height: 200px;
	width: 160px;
	text-align: center;
	margin: 11px 4px;
	border: 1px solid #666;
	border-radius: 3px;
	padding: 0;
}

.item-logo {
	height: 50%;
	width: 100%;
	padding: 5px 3px;
	object-fit: scale-down;
}

hr {
	margin: 5px 0;
	padding: 0;
}

#like-button {
	border: none;
	border-radius: 3px;
	background-color: green;
	padding: 2px 15px;
	color: white;
}

#share-button {
	border: none;
	border-radius: 3px;
	background-color: cyan;
	padding: 2px 15px;
}

#like-share-form {
	border-top: 1px solid #aaa;
	background-color: #ddd;
	padding: 13px 0;
}

.text {
	margin-bottom: 10px;
	font-weight: bold;
}

.item-wapper a {
	color: black;
	text-decoration: none;
}
</style>
<title>san pham</title>
</head>
<body>
	<c:set var="user" value="<%=user%>" />
	<div class="row">
		<div class="col-md-9">
			<div class="row item-wappers">
				<c:forEach var="item" items="${videos}">
					<div class="item-wapper">
						<a href="/asm/detailVideo?videoId=${item.getId()}"> <img
							src="img/${item.getPoster()}" alt="" class="item-logo">
							<hr />
							<p class="text">${item.getTitle()}</p>
							<form id="like-share-form" method="get">
								<%
								Boolean i = true;
								%>
								<c:set var="favorites"
									value="${item.getFavorites() == null ? null : item.getFavorites()}" />
								<c:forEach var="favorite" items="${favorites}">

									<c:set var="user1" value="${favorite.getUser() }" />
									<c:choose>
										<c:when test="${user1.getId().equals( user.getId())}">
											<a href="/asm/like/remove?id=${item.getId() }&url=home"
												style="border: 1px solid black; background-color: red; color: white; padding: 4px 9px;">UnLike</a>
											<%
											i = false;
											%>
										</c:when>
									</c:choose>


								</c:forEach>
								<c:if test="<%=i%>">
									<a href="/asm/like/add?id=${item.getId() }&url=home"
										style="border: 1px solid black; background-color: green; color: white; padding: 4px 9px;">Like</a>
								</c:if>
								<button class="share-button" data="${item.getId() }">share</button>
							</form>
						</a>
					</div>
				</c:forEach>
			</div>
			<div class="row">
				<div class="col">
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link"
								href="/asm/home?page=<%=(request.getParameter("page") == null
		? 1
		: Integer.valueOf(request.getParameter("page")) > 1
				? Integer.valueOf(request.getParameter("page")) - 1
				: request.getParameter("page"))%>&title=<%=(request.getParameter("title") == null ? "%%" : request.getParameter("title"))%>"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									<span class="sr-only">Previous</span>
							</a></li>
							<%
							Object size = request.getAttribute("size");
							int i = 0;
							do {
							%>
							<li class="page-item"><a class="page-link"
								href="/asm/home?page=<%=i + 1%>&title=<%=(request.getParameter("title") == null ? "%%" : request.getParameter("title"))%>"><%=i + 1%></a></li>
							<%
							i++;
							} while (i < Math.ceil((double) size));
							%>
							<li class="page-item"><a class="page-link"
								href="/asm/home?page=<%=(request.getParameter("page") == null
		? 2
		: Integer.valueOf(request.getParameter("page")) < (Math.ceil((double) request.getAttribute("size")))
				? Integer.valueOf(request.getParameter("page")) + 1
				: request.getParameter("page"))%>&title=<%=(request.getParameter("title") == null ? "%%" : request.getParameter("title"))%>"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
									class="sr-only">Next</span>
							</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>

		<div class="col-md-3">


			<div class="card">
				<div class="card-header">
					<i class="fa fa-align-right" aria-hidden="true"><ion-icon
							name="thumbs-up-outline"></ion-icon> Sở thích</i>
				</div>
				<ul class="list-group list-group-flush">
					<a href="/asm/home?title=K&page=1" style="text-decoration: none;"><li
						class="list-group-item ">Kinh dị</li></a>
					<a href="/asm/home?title=c&page=1" style="text-decoration: none;"><li
						class="list-group-item ">Nhạc</li></a>
					<a href="/asm/home?title=t&page=1" style="text-decoration: none;"><li
						class="list-group-item ">Sự thật</li></a>
					<a href="/asm/home?title=m&page=1" style="text-decoration: none;"><li
						class="list-group-item ">Review sản phẩm</li></a>
				</ul>
			</div>
		</div>
	</div>
	<script>
		var doNotThing = (e) => {
			e.preventDefault();
		}
		document.querySelectorAll(".like-button").forEach((v) => {
			v.addEventListener("click", doNotThing);
		});
	</script>
</body>
</html>