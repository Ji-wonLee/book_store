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
<script>
	function goMenu(){
		alert("발주신청이 확정되었습니다. 메인화면으로 돌아갑니다.");
		window.location.href = "toAdminMain";
	}
</script>
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
				<li><a href="http://localhost:8080/sms">로그아웃</a></li>
			</ul>
		</nav>
		<article id="article">
			<!-- 여기에 출력할 코드를 작성(list라던가) -->
			<form action="/sms/toAdminMain" method="get">
				<!-- hidden input 필드에 orderList를 전달 -->
				<c:forEach var="product" items="${orderList}">
					<input type="hidden" name="${product.key}" value="${product.value}">
				</c:forEach>
				<input type="submit" id="orderComplete" value="발주확정" onclick="goMenu(); return false;">
				<table>
					<tr>
						<th>상품 id</th>
						<th>수량</th>
					</tr>
					<c:forEach var="product" items="${orderList}">
						<tr>
							<td>${product.key}</td>
							<td>${product.value}</td>
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