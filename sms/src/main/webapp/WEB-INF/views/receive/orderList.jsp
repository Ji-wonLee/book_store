<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>발주서 선택</title>
</head>
<body>
	<form id="order_id" action="/sms/updateReceive" method="post" >
		<button type="submit">발주선택</button>
		<table border="1">
		<tr>
				<th></th>
				<th>발주ID</th>
				<th>발주날짜</th>
				<th>작성자</th>
				<th>총결제액</th>
			</tr>
			<c:forEach var="order" items="${orderIdList}">
				<tr>
					<td><input type="radio" name="order_id" value="${order.order_id}"><br></td>
					<td>${order.order_id}</td>
					<td>${order.order_date}</td>
					<td>${order.writer}</td>
					<td>${order.totalprice}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>