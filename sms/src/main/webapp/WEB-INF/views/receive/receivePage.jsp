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
	 <form action="/sms/toInventory" method="post" >
		<button type="submit">수정완료</button><br>
		${rdList[0].receive_id}
		<input type="hidden" name="receive_id" value="${rdList[0].receive_id}">
		<table border="1">
			<tr>
				<th>상품명</th>
				<th>가격</th>
				<th>수량</th>
				<th>수정수량</th>
			</tr>
			<c:forEach var="receiveDetail" items="${rdList}">
				<tr>
					<td>${receiveDetail.product_id}</td>
					<td>${receiveDetail.price}</td>
					<td>${receiveDetail.quantity}</td>
					<td><input type="number" name="${receiveDetail.product_id}"
						value="${receiveDetail.quantity}"></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>