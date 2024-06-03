<%@page import="sms.dto.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
</head>
<body>
	<div>
		<h3>사용자 님 환영합니다.</h3>
		<ul>
			<li><a href="http://localhost:8080/sms">내 정보 조회</a></li>
			<li><a href="http://localhost:8080/sms">장바구니</a></li>
			<li><a href="http://localhost:8080/sms/mainTest">상품 조회</a></li>
		</ul>
	</div>
	<p>---</p>
	<form action="/sms/searchTest" method="get">
	<div>
		<select name = "categorySelect" id = "category">
			<c:forEach var = "Category" items = "${categorylist}">
				<option value = "${Category.category_id}">${Category.category_name}</option>
			</c:forEach>
		</select>
		<input type="text" id="searchText" placeholder="검색어 입력"/> <!-- 검색어 입력 text 박스 -->
		<input type="submit" value="검색"/>
	</div>
	<div class = "productlist">
		<table>
			<tr>
				<th> </th>
				<th>분류</th>
				<th>도서명</th>
				<th>페이지</th>
				<th>가격</th>
				<th>판매 상태</th>
			</tr>
		<c:forEach var="product" items="${productlist}" varStatus="idx">
			<tr>
				<td>${idx.count}</td>
				<td>${product.category_name}</td>
				<td><a href="<c:url value='/bookInfo'><c:param name='product_id' value='${product.product_id}'/></c:url>">${product.product_name}</a></td>
				<td>${product.product_page}</td>
				<td>${product.product_price}</td>
				<td>${product.state}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	</form>
</body>
</html>