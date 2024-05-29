<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>발주확인</title>
</head>
<body>
<form action="/sms/orderComplete" method="post">
	<button type="submit">발주확인</button>
	<table border="1">
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
</body>
</html>