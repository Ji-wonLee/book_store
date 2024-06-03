<%@ page import="sms.dto.CartDto"%>
<%@ page import="sms.dto.ProductDto"%>
<%@ page import="sms.dto.PaymentDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart Items</title>
</head>
<body>
    <h1>Cart Items</h1>
    <c:choose>
        <c:when test="${not empty error}">
            <p>${error}</p>
        </c:when>
        <c:otherwise>
            <table border="1">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td>${item.product_id}</td>
                            <td>${item.product_name}</td>
                            <td>${item.quantity}</td>
                            <td>${item.price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form action="/sms/payment/checkStock" method="post">
                <input type="hidden" name="user_id" value="DGo9fGM" />
                <button type="submit">결제</button>
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>