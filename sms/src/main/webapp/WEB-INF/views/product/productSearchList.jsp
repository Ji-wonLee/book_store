
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
	table {
		border-collapse: collapse;
		width : 100%';
	}
	th {
		border-bottom: 1px solid #dddddd;
  		text-align: center;
  		padding: 8px;
	}
	td {
		border-bottom: 1px solid #dddddd;
  		text-align: left;
  		padding: 8px;
	}
</style>

<meta charset="UTF-8">
<title>productSearch</title>
</head>
<body>
	<form action = "/sms/searchList" method = "GET"> <!-- action 연결 필요 -->
	
	<div class = "userIndex"> <!-- 좌측 유저정보 및 사이트 목록 표시 -->
		
	</div>
	
	<div class = "searchBox"> <!-- 상단 검색 구성 -->
		<select name = "category" id = "categoryId"> <!-- 분류 드롭 다움 메뉴 -->
		<c:forEach var="category" items="${categoryList}" >
			<option value = "${category.id}">${category.name}</option>
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
				<th>품명</th>
				<th>가격</th>
				<th>페이지</th>
			</tr>
			<c:forEach var="product" items="${productList}" varStatus="idx">
			<tr>
				<td>${idx.count}</td>
				<td>${product.categoryName}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.page}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	</form>
</body>
</html>