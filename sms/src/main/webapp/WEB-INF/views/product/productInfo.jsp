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
		border-collapse: separate;
		width : 100%';
	}
	
	article th {
		border-bottom: 1px solid #dddddd;
  		text-align: center;
  		padding: 8px;
  		background-color: #ccc4d4;
	}
	
	article td {
		border-bottom: 1px solid #dddddd;
  		text-align: left;
  		padding: 8px;
  		background-color: white;
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
<title>상품 정보</title>
</head>
<body>
	<form action = "/sms/searchList" method = "GET"> <!-- action 연결 필요 -->
	<header></header>
	<section>
		<nav>
			<!-- 좌측 유저정보 및 사이트 목록 표시 -->
				<div class = "userText">
				<h3>${user.name}</h3>
				<h3>님 환영합니다. </h3>
				</div>
				
				<ul>
     				<li><a href="#">내 정보 조회</a></li>
    				<li><a href="#">장바구니</a></li>
      				<li><a href="#">상품 조회</a></li>
   				</ul>
		</nav>
		<article>
			<div class = "searchBox"> <!-- 상단 검색 구성 -->
				<select name = "category" id = "categoryId"> <!-- 분류 드롭 다움 메뉴 -->
					<c:forEach var="category" items="${categoryList}" >
						<option value = "${category.id}">${category.name}</option>
					</c:forEach>
				</select>
				<input type = "text" id = "searchText" placeholder="검색어 입력"/> <!-- 검색어 입력 text 박스 -->
				<input type = "submit" value="검색"/> <!-- 분류, 검색어 송신 버튼 -->
			</div>
			<div class="productInfo">
    <div class="productImage">
        <img src="${product.imgUrl}" alt="Product Image">
    </div>
    <div class="productDetails">
        <table>
            <tr>
                <th>도서명</th>
                <td>${product.bookName}</td>
            </tr>
            <tr>
                <th>분류</th>
                <td>${product.category}</td>
            </tr>
            <tr>
                <th>페이지 수</th>
                <td>${product.pageCount}</td>
            </tr>
            <tr>
                <th>설명</th>
                <td>${product.description}</td>
            </tr>
            <tr>
                <th>제조업체 명</th>
                <td>${product.manufacturer}</td>
            </tr>
            <tr>
                <th>가격</th>
                <td>${product.price}</td>
            </tr>
        </table>
    </div>
</div>
		</article>
	</section>
	</form>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>