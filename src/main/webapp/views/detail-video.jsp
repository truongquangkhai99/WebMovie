<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ page import="com.asm.model.*"%>
<%@ page import="java.util.*"%>
<%
Users user = (Users) request.getSession().getAttribute("user");
Boolean admin = (user == null ? false : user.getAdmin());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>detail video</title>
<style>
.container-detail-item {
	display: flex;
	gap: 0.7em;
	flex-wrap: wrap;
	min-height: 500px;
}

.text {
	text-align: center;
	font-size: 1.2rem;
	font-weight: bold;
}

.item-wapper {
	flex: 2 1 600px;
	height: max(500px, 20vh);
}

.video-play {
	height: 70%;
	width: 100%;
}

.suggest-video {
	flex: 1 1 300px;
}

#like-share-form {
	display: flex;
	justify-content: flex-end;
}

#like-button {
	border: none;
	border-radius: 3px;
	background-color: green;
	padding: 5px 15px;
	color: white;
	margin-right: 5px;
}

#share-button {
	border: none;
	border-radius: 3px;
	background-color: cyan;
	padding: 5px 15px;
}

a {
	text-decoration: none;
}

.video-wrapper {
	height: 100px;
	width: 100%;
	margin: 7px 11px;
	color: black;
	font-weight: bold;
	border: 1px solid #bbb;
	padding: 7px 4px;
}

img {
	height: 86px;
	width: 40%;
	object-fit: cover;
}

.video-title {
	margin: 0 3px;
}

#like-share-form>h2 {
	margin-right: auto;
	color: blue;
}
</style>
</head>
<body>
	<div class="container-detail-item">
		<div class="item-wapper">
			<%
			List<Videos> videos = (List<Videos>) request.getAttribute("videos");
			String videoId = request.getParameter("videoId");
			for (Videos video : videos) {
				if (video.getId().equals(videoId)) {
			%>
			<iframe class="video-play" src="<%=video.getDescription()%> "
				title="YouTube video player" frameborder="0"
				allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
				allowfullscreen></iframe>
			<hr />
			<p class="text"><%=video.getTitle()%></p>
			<form id="like-share-form" method="get">
				<h2>
					View:
					<%=video.getViews()%></h2>
				<%
				Boolean i = true;
				List<Favorites> favorites = video.getFavorites();
				%>
				<c:forEach var="favorite" items="<%=favorites%>">
					<c:set var="user1" value="${favorite.getUser() }" />
					<c:if test="${user1.getId() == user.getId()}">
						<a href="/asm/like/remove?id=<%=video.getId()%>&url=detailVideo?videoId=<%=video.getId() %>"
							style="border: 1px solid black; background-color: red; padding: 4px 9px; color: white; margin-right: 10px;">UnLike</a>
						<%
						i = false;
						%>
					</c:if>
				</c:forEach>
				<c:if test="<%=i%>">
					<a href="/asm/like/add?id=<%=video.getId()%>&url=detailVideo?videoId=<%=video.getId() %>"
						style="border: 1px solid black; background-color: green; padding: 4px 9px; color: white; margin-right: 10px;">Like</a>
				</c:if>
				<button id="share-button" data="<%=video.getId()%>">share</button>
			</form>
			<%
			}
			}
			%>

		</div>
		<div class="suggest-video">
			<%
			for (Videos video : videos) {
				if (!video.getId().equals(videoId)) {
			%><a href="/asm/detailVideo?videoId=<%=video.getId()%>">
				<div class="video-wrapper">
					<img src="img/<%=video.getPoster()%>" alt="loi hinh"> <span
						class="video-title"><%=video.getTitle()%></span>
				</div>
			</a>
			<%
			}
			}
			%>
		</div>
	</div>
	<script>
		const shareButton = document.querySelector("#share-button");
		shareButton.addEventListener("click", (e) => {
				e.preventDefault();
				e.stopPropagation();
				shareWrapper.classList.add("open");
				videoId.value = shareButton.getAttribute("data"); 
		});
	</script>
</body>
</html>