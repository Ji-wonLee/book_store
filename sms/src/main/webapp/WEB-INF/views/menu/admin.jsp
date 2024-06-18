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
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style>
* {
	box-sizing: border-box;
}

nav {
	float: left;
	width: 20%;
	height: 1000px;
	background: #ffd9df;
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
	<div>
		<a href="#" onClick="history.go(0)">
			<img src="resources/logo_01.png" height="200"/>
		</a>
	</div>
	<section>
		<nav>
			<c:set var="userLoginStt" value="${userLoginStt}" />
			<c:set var="userClientStt" value="${userClientStt}" />
			<fieldset
				style='width: 160px; border: none; margin-left: -20px; margin-top: -20px;'>

				${userName}님<br>
				어서오세요<br> <button type="button" onclick="location.href='index.jsp'">로그아웃</button>

				<br><hr style="width:158px">
					<form action="/sms/orderList" method="get">
						<input type="submit" value="발주" style="background-color:transparent;border:none;width:158px;height:100%;text-align:left">
					</form>
					<hr>
					
					<form action="/sms/receive" method="get">
						<input type="submit" value="입고" style="background-color:transparent;border:none;width:158px;height:100%;text-align:left">
					</form>
					<hr>
					
					<form action="/sms/inventory" method="get">
						<input type="submit" value="재고" style="background-color:transparent;border:none;width:158px;height:100%;text-align:left">
					</form>
					<hr>
					
					<form action="/sms/admStt" method="get">
						<input type="submit" value="사용자" style="background-color:transparent;border:none;width:158px;height:100%;text-align:left">
					</form>
			</fieldset>
		</nav>
		<article></article>
	</section>
</body>
<footer>
	<p>- 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
</footer>
</html>