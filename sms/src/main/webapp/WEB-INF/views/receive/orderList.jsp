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
<style>
* {
	box-sizing: border-box;
}

section {
	display: flex;
	flex-direction: row;
	align-items: stretch;
}

nav {
	float: left;
	width: 20%;
	background: #9ec7ae;
	padding: 20px;
}

article {
	float: left;
	padding: 20px;
	width: 80%;
	background-color: #dbf0e6;
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
<title>재고 관리 화면</title>
</head>
<body>
	<section>
		<nav id="nav">
			<!-- 좌측 유저정보 및 사이트 목록 표시 -->
			<div class="userText">
				<h3>관리자</h3>
				<h3>님 환영합니다.</h3>
			</div>
			<ul>
				<li><a href="http://localhost:8080/sms">사용자 관리</a></li>
				<li><a href="http://localhost:8080/sms">재고 관리</a></li>
				<li><a href="http://localhost:8080/sms">발주 관리</a></li>
				<li><a href="http://localhost:8080/sms">입고 관리</a></li>
			</ul>
		</nav>
		<article id="article">
			<!-- 여기에 출력할 코드를 작성(list라던가) -->
			<form id="order_id" action="/sms/updateReceive" method="post">
				<button type="submit">발주선택</button>
				<table>
					<tr>
						<th></th>
						<th>발주ID</th>
						<th>발주날짜</th>
						<th>작성자</th>
						<th>총결제액</th>
					</tr>
					<c:forEach var="order" items="${orderIdList}">
						<tr>
							<td><input type="radio" name="order_id"
								value="${order.order_id}"><br></td>
							<td>${order.order_id}</td>
							<td>${order.order_date}</td>
							<td>${order.writer}</td>
							<td>${order.totalprice}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</article>
	</section>
	<script>
        <!-- 화면 크기가 변경될 때마다 높이를 조정-->
        window.addEventListener('resize', function() {
            var section = document.getElementById('section');
            var article = document.getElementById('article');
            section.style.height = article.offsetHeight + 'px';
        });

        <!-- 초기 로드 시에도 높이를 조정-->
        window.dispatchEvent(new Event('resize'));
    </script>
	<footer>
		<p>- 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>