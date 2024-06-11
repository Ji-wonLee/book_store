<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="oracle.jdbc.*"%>
<%@ page import="oracle.jdbc.pool.OracleDataSource"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 추가할부분 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 추가할부분 -->
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- 추가할부분 -->
<style>
* {
	box-sizing: border-box;
}

nav {
	float: left;
	width: 20%;
	height: 1000px;
	background: #c2c2d6;
	padding: 20px;
}

article {
	overflow: auto;
	float: left;
	padding: 20px;
	width: 80%;
	height: 1000px;
	background-color: #f2f2f2;
}

article table {
	border-collapse: collapse;
	width: 100%';
}

article th {
	border-bottom: 1px solid #dddddd;
	text-align: center;
	padding: 8px;
}

article td {
	border-bottom: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

section::after {
	content: "";
	display: table;
	clear: both;
}

footer {
	padding: 10px;
	text-align: center;
	color: #8c8c8c;
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
	<!-- <form action = "/sms/searchList" method = "GET"> -->
	<!-- action 연결 필요 -->
	<section>
		<nav>
			<c:set var="userLoginStt" value="${userLoginStt}" />
			<c:set var="userClientStt" value="${userClientStt}" />
			<fieldset
				style='width: 160px; border: none; margin-left: -20px; margin-top: -20px;'>

				${user_id}님, 어서오세요<br> <a
					href="javascript:window.history.back();">로그아웃</a> <br>
				<ul>
					<li><a href="/sms/order">발주</a></li>
					<li><a href="/sms/receive">입고</a></li>
					<li><a href="/sms/inventory">재고</a></li>
					<li><a href="/sms/admStt">사용자</a></li>
				</ul>
			</fieldset>

		</nav>
		<article></article>
	</section>
	<!-- </form> -->
</body>
<footer>
	<p>- 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
</footer>
</html>