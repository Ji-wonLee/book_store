<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>장바구니</title>
</head>
<body>
    <h1>장바구니</h1>
    <a href="/main">메인화면</a>
    <table border="1">
        <thead>
            <tr>
                <th>품번</th>
                <th>상품명</th>
                <th>개수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${cartItems}">
                <tr>
                    <td>${item.productId}</td>
                    <td>${item.productName}</td>
                    <td>
                        <form:form action="/cart/update" method="post">
                            <form:hidden path="cartId" value="${item.cartId}" />
                            <form:hidden path="productId" value="${item.productId}" />
                            <form:hidden path="userId" value="${item.userId}" />
                            <form:input path="quantity" value="${item.quantity}" />
                            <input type="submit" value="수정" />
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/payment/payPage?userId=${userId}">결제</a>
</body>
</html>
