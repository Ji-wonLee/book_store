
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
		float: left;
		padding: 20px;
 		width: 80%;
 		height: 1000px;
 		background-color: #f2f2f2;
	}
	
	article table {
		border-collapse: collapse;
		width : 100%';
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
 		color : #8c8c8c;
	}
	
	@media (max-width: 600px){
  		nav, article {
   			 width: 100%;
    		height: auto;
 		 }
	}
</style>
<title>회원 메인 페이지</title>
</head>
<body>
	<header></header>
	<section>
		<nav>
			<!-- 좌측 유저정보 및 사이트 목록 표시 -->
				<div class = "userText">
				<h3>사용자 </h3>
				<h3>님 환영합니다. </h3>
				</div>
				
				<ul>
     				<li><a href="#">내 정보 조회</a></li>
    				<li><a href="#">장바구니</a></li>
      				<li><a href="#">상품 조회</a></li>
   				</ul>
		</nav>
		<article>
		<form action = "/sms/searchList" method = "GET"> <!-- action 연결 필요 -->
			<div class = "searchBox"> <!-- 상단 검색 구성 -->
				<select name = "category" id = "categoryId"> <!-- 분류 드롭 다움 메뉴 -->
					<c:forEach var="category" items="${categoryList}" >
						<option value = "${category.category_id}">${category.category_name}</option>
					</c:forEach>
				</select>
				<input type = "text" id = "searchText" placeholder="검색어 입력"/> <!-- 검색어 입력 text 박스 -->
				<input type = "submit" value="검색"/> <!-- 분류, 검색어 송신 버튼 -->
			</div>
	
			<div class = "productlist"> <!-- 상품 출력을 위한 테이블 -->
				<table>
					<tr>
						<th> </th>
						<th>분류</th>
						<th>도서명</th>
						<th>페이지</th>
						<th>가격</th>
						<th>판매 상태</th>
					</tr>
					<c:forEach var="product" items="${productList}" varStatus="idx">
						<tr>
							<td>${idx.count}</td>
							<td>${product.category_name}</td>
							<td>${product.product_name}</td>
							<td>${product.product_page}</td>
							<td>${product.product_price}</td>
							<td>${product.state}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
		</article>
	</section>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>