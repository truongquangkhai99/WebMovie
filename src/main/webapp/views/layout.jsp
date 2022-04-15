<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ page import="com.asm.model.*"%>
<% Users user = (Users) request.getSession().getAttribute("user"); 
Boolean admin = (user == null ? false : user.getAdmin());%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>${page.getTitle() }</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<style>
.logo {
	height: 60px;
	width: 50px;
	object-fit: scale-down;
}

.row.item-wappers {
	min-height: 535px;
	width: 100%;
	justify-content: center;
}
#share-form{
	position: absolute;
	display: flex;
	flex-direction: column;
	gap: 10px;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 400px;
	height: 240px;
	border: 1px solid black;
	background-color: white;
	border-radius: 10px;
}
.share-wrapper.open{
	height: 100vh;
	width: 100vw;
	position: fixed;
	background-color: rgba(40, 40, 40, 0.3);
	z-index: 100;
	display: block;
}
.share-wrapper{
	display: none;
}
#exit-share{
	align-self: flex-end;
	margin-right: 3px;
	border: none;
	background-color: transparent;
}
#share-form input{
	width: 70%;
	align-self: center;
	border: 1px solid black;
	outline: none;
	padding: 3px 5px;
	font-size: 1.1em;
	border-radius: 4px;
}
#share-form .button-share{
	width: 20%;
	align-self: center;
	border-radius: 5px;
	background-color: orange;
}
.video-id{
	text-align: center;
}
</style>
</head>

<body>
	<div class="share-wrapper">
		<form action="/asm/share" method="post" id="share-form">
			<button id="exit-share">X</button>
			<input type="text" name="email" class="email-to-share" placeholder="Email to send">
			<h3 class="video-id">Video id: <br/><input type="text" name="id" readonly></h3>
			<button class="button-share">Share</button>
		</form>
	</div>
	<div class="container-fluid">
		<header class="row pt-5 pb-2">
			<div class="col-9">
				<h1>Video Entertainment</h1>
			</div>
			<div class="col-3 text-right">
				<img src="${req.getRequestURI().toString()}/asm/img/logo.png" width="300px" class="mr-4 logo" />
			</div>
		</header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="<%= admin ? "/asm/admin/user" : "/asm/home"%>" > <img alt=""
					src="${req.getRequestURI().toString()}/asm/img/logo.png" width="60px" height="30px" class="logo">
					<%= admin ? "Users" : "OnEn"%>
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarScroll"
					aria-controls="navbarScroll" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarScroll">
					<ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll"
						style="-bs-scroll-height: 100px;">
						<li class="nav-item"><a class="nav-link" href="<%= admin ? "/asm/admin/video" : "/asm/home"%>">
								<i class="fa fa-align-left" aria-hidden="true"></i> <ion-icon
									name="<%= admin ? "videocam-outline" : "home-outline"%>"></ion-icon> <%= admin ? "Videos" : "Home"%>
						</a></li>
						<%if(!admin){ %>
						<li class="nav-item"><a class="nav-link" href="/asm/hobbies"> <i
								class="fa fa-comments" aria-hidden="true"></i> <ion-icon
									name="thumbs-up-outline"></ion-icon>Hobbies
						</a></li>
						<%} else if(admin){%>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarScrollingDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"><i
								class="fa fa-user" aria-hidden="true"></i> <ion-icon
									name="list-outline"></ion-icon> Report </a>
							<ul class="dropdown-menu"
								aria-labelledby="navbarScrollingDropdown">
									<li><a class="dropdown-item" href="/asm/admin/report/mostLikeVideo">Most like Videos</a></li>
									<li><a class="dropdown-item" href="/asm/admin/report/mostShareVideo">Most Share Videos</a></li>
									<li><a class="dropdown-item" href="/asm/admin/report/mostViewVideo">Most View Videos</a></li>
							</ul></li>
							<%}%>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarScrollingDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"><i
								class="fa fa-user" aria-hidden="true"></i> <ion-icon
									name="person-outline"></ion-icon> Tài khoản </a>
							<ul class="dropdown-menu"
								aria-labelledby="navbarScrollingDropdown">
								<c:if test="${user == null}">
									<li><a class="dropdown-item" href="/asm/login">Đăng nhập</a></li>
									<li><a class="dropdown-item" href="/asm/forgetPassword">Quên mật khẩu</a></li>
									<li><a class="dropdown-item" href="/asm/register">Đăng kí</a></li>
								</c:if>
								<c:if test="${user != null}">
									<li><a class="dropdown-item" href="/asm/logout">Đăng xuất</a></li>
								</c:if>
							</ul></li>

					</ul>
					<form class="d-flex" action="/asm/home?page=1" method="get">
						<input class="form-control me-2" type="search"
							placeholder="Search" aria-label="Search" name="title">
						<button class="btn btn-outline-success" type="submit">search</button>
					</form>
				</div>
			</div>
		</nav>

		<jsp:include page='${page.getContentUrl()}'></jsp:include>
		


		<footer class="row">
			<div class="col text-center border-top">
				<strong>FPT polytechnic &copy;2022.Nguyễn Như Tài</strong>
			</div>

		</footer>
	</div>
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script>
		<% if (request.getAttribute("message") != null){
			%> alert("<%= request.getAttribute("message")%>"); <%
		}%>
		const exitShare = window.document.querySelector("#exit-share");
		const shareWrapper = window.document.querySelector(".share-wrapper");
		const shareButtons = document.querySelectorAll(".share-button");
		const videoId = window.document.querySelector(".video-id input");
		const shareForm = window.document.querySelector("#share-form");
		exitShare.addEventListener("click", (e) => {
			e.preventDefault();
			shareWrapper.classList.remove("open");
		});
		shareButtons.forEach((v) => {
			v.addEventListener("click", (e) => {
				e.preventDefault();
				e.stopPropagation();
				shareWrapper.classList.add("open");
				videoId.value = v.getAttribute("data"); 
			});
		});
		document.addEventListener("click", function(evt) {
			evt.stopPropagation();
	          targetEl = evt.target;
	        do {
	          if(targetEl == shareForm) {
	            return;
	          }
	          targetEl = targetEl.parentNode;
	        } while (targetEl);
	        if(shareWrapper.classList.contains("open")){
	        	shareWrapper.classList.remove("open");
	        }
	      });
	</script>
</body>

</html>