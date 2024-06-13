<%@page import="sms.dto.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>발주 상세 목록 페이지</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap')
	;

@import
	url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR&display=swap')
	;

.header-container {
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 500;
	font-style: normal;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
	padding: 0 20px;
}

.header-container h3 {
	margin: 0;
}

nav {
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 500;
	font-style: normal;
	width: 100%;
	background: #c2c2d6;
	padding: 10px 0;
}

nav ul {
	display: flex;
	justify-content: space-around;
	list-style: none;
	padding: 0;
	margin: 0;
}

nav ul li {
	margin: 0;
}

nav ul li a {
	text-decoration: none;
	color: black;
}

.search {
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 500;
	font-style: normal;
	border: 2px solid purple;
	border-radius: 30px;
	background-color: #eaeafb;
	text-align: center;
	margin-top: 22px;
	height: 30px;
	width: 900px;
}

footer {
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 500;
	font-style: normal;
	padding: 10px;
	text-align: center;
	color: #8c8c8c;
}

.table-container {
	font-family: "Hahmlet", serif;
	font-optical-sizing: auto;
	font-weight: 400;
	font-style: normal;
	margin-top: 20px;
}

table {
	font-family: "Hahmlet", serif;
	font-optical-sizing: auto;
	font-weight: 400;
	font-style: normal;
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border-bottom: 1px solid #ddd;
	padding: 8px;
}

th {
	text-align: center;
	background-color: #f2f2f2;
	text-align: left;
}

td img {
	text-align: left;
	max-width: 50px;
	height: auto;
}

.pageBar {
	text-align: center;
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 500;
	font-style: normal;
	font-size: 1.5em;
	padding: 8px 20px;
}

.page-link {
	display: inline-block;
	color: black;
	float: center;
	padding: 8px 20px;
	text-decoration: none;
}

.page-link:hover:not(.active) {
	background-color: #c2c2d6;
	border-radius: 5px;
}
.input {
			font-family: "Noto Sans KR", sans-serif;
			font-optical-sizing: auto;
			font-weight: 500;
			font-style: normal;
			padding: 7px;
			}

@media ( max-width : 600px) {
	nav, article {
		width: 100%;
		height: auto;
	}
}
</style>
</head>
<body>
	<div class="header-container">
		<h3>${user_id}님환영합니다.</h3>
		<img src="main/resources/logo_01.png" />
		<button type="button" onclick="location.href='index.jsp'">로그아웃</button>
	</div>
	<nav>
		<ul>
			<li><a href="/sms/toAdminMain">메인화면</a></li>
		</ul>
	</nav>
	<section>
		
		<div class="max-w-4xl mx-auto">
		<!-- 여기에 출력할 코드를 작성(list라던가) -->
			<form action="/sms/orderList" method="get">
			<div class="input" style="text-align: right;">
				<button style="border-radius: 3px; width: 100px; height: 34px; background-color : #c2c2d6;" type="submit">발주목록</button>
			</div>
				<table>
					<tr>
						<th>상품_id</th>
						<th>수량</th>
						<th>가격</th>
					</tr>
					<c:forEach var="orderDetail" items="${orderDetailList}">
						<tr>
							<td>${orderDetail.product_id}</td>
							<td>${orderDetail.quantity}</td>
							<td>${orderDetail.price}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	</section>
	<footer>
		<p>- 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>