<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   isELIgnored="false" pageEncoding="UTF-8"%>
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
	<section>
		<nav>
			<!-- 좌측 유저정보 및 사이트 목록 표시 -->
				<div class = "userText">
				<h3>사용자</h3> <!-- session 과 사용자 정보를 가져옴, 이름 출력 -->
				<h3>님 환영합니다. </h3>
				</div>
				
				<ul>
     				<li><a href="/sms/myInfo">내 정보 조회</a></li>
					<li><a href="http://localhost:8080/sms/list">장바구니</a></li>
					<li><a href="http://localhost:8080/sms/customermain">상품 조회</a></li>
   				</ul>
		</nav>
		<article>
		<form action = "/sms/list" method = "GET"> <!-- action 연결 필요 -->
			<div class="productInfo">
    			<div class="productImage">
        			<img src="${product_imgurl}" alt="Product Image">
    			</div>
    			<div class="productDetails">
        			<table>
            			<tr>
                			<th>도서명</th>
               			 	<td>${product_name}</td>
            			</tr>
            			<tr>
                			<th>분류</th>
                			<td>${category_name}</td>
            			</tr>
			            <tr>
			                <th>페이지 수</th>
			                <td>${product_page}</td>
			            </tr>
			            <tr>
			                <th>설명</th>
			                <td>${description}</td>
			            </tr>
			            <tr>
			                <th>제조업체 명</th>
			                <td>${manufacture_name}</td>
			            </tr>
			            <tr>
			                <th>가격</th>
			                <td>${product_price}</td>
			            </tr>
			            <tr>
			                <th>판매현황</th>
			                <td>${state}</td>
			            </tr>
			        </table>
    			</div>
    			<%-- <input type="number" name="${product_id}" value="0" > --%>
    			<input type="button" id="onCart" value="장바구니 추가">
			</div>
		</form>
		</article>
	</section>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>