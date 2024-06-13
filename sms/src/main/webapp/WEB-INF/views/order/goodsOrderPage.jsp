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
<title>발주 페이지</title>
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

.input {
	font-family: "Noto Sans KR", sans-serif;
	font-optical-sizing: auto;
	font-weight: 500;
	font-style: normal;
	padding: 7px;
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

@media ( max-width : 600px) {
	nav, article {
		width: 100%;
		height: auto;
	}
}
</style>
<script>
	function filterAndSubmitForm() {
        const form = document.getElementById('orderForm');
        const inputs = form.querySelectorAll('input[type="number"]');
        inputs.forEach(input => {
            if (input.value == '0') {
                input.name = '';
            }
        });
        //여기에 코드 추가
        let hasSelection = Array.from(inputs).some(input => input.value !== '0' && input.value.trim() !== '');
    if (!hasSelection) {
        alert("하나 이상의 상품을 선택하세요.");
        return false;
    }
        form.submit();
    }
</script>
</head>
<body>
	<div class="header-container">
		<h3>${user_id}님환영합니다.</h3>
		 <a href="/sms/toAdminMain"><img style="height:100px; width:auto;" src="resources/logo_01.png" alt="logo" width=auto height="100px"/></a>
		<button type="button" onclick="location.href='index.jsp'">로그아웃</button>
	</div>
	<nav>
		<ul>
			<li><a href="/sms/toAdminMain">메인화면</a></li>
		</ul>
	</nav>
	<section>
		<div class="max-w-4xl mx-auto">

			<div class="search">
				<form action="/sms/orderSearch" method="get">
					<select style="background-color: #eaeafb;" name="category_id"
						id="category_id">
						<option value="all">전체</option>
						<c:forEach var="Category" items="${categorylist}">
							<option value="${Category.category_id}">${Category.category_name}</option>
						</c:forEach>
					</select> 
					<input style="width: 600px; background-color: #eaeafb;"
						type="text" name="searchtext" id="searchText" placeholder="검색어 입력" />
					<input style="width: 70px; background-color: #eaeafb;"
						type="number" name="remaining" id="remaining" value="999999"
						placeholder="N개 이하 검색" /> 
					<input
						style="background-color: #eaeafb; background-image: 'resources/search.png'; border: none; background-repeat: no-repeat; background-size: 100% 100%;"
						type="submit" value="검색" />
				</form>
			</div>
		</div>
		<div class="max-w-4xl mx-auto">
			<form id="orderForm" action="/sms/orderCheck" method="get"
				onsubmit="filterAndSubmitForm(); return false;">

				<div class="input" style="text-align: right;">
					<button style="border-radius: 3px; width: 100px; height: 34px; background-color : #c2c2d6;" type="submit">발주선택</button>
					
				</div>
				<table>
					<tr>
						<th>상품 id</th>
						<th>상품명</th>
						<th>가격</th>
						<th>회사명</th>
						<th style="width:60px">분류</th>
						<th>표지 url</th>
						<th style="width:60px">상태</th>
						<th style="width:80px">잔여수량</th>
						<th style="width:120px">신청수량(입력)</th>
					</tr>
					<c:forEach var="product" items="${productList}">
						<tr>
							<td>${product.product_id}</td>
							<td>${product.product_name}</td>
							<td>${product.product_price}</td>
							<td>${product.manufacture_name}</td>
							<td>${product.category_name}</td>
							<td><img src="${product.product_imgurl}" alt="image"
								width="50" height="60"></td>
							<td>${product.state}</td>
							<td>${product.quantity}</td>
							<td style="background-color: #eaeafb;"><input style="width:100px; background-color: #eaeafb;" type="number"
								name="${product.product_id}_${product.product_price}" value="0"
								min="0"></td>
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