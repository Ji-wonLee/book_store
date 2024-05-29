<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>결제 진행</title>
</head>
<body>
    <h1>결제 진행</h1>
    <a href="/main">메인화면</a>
    <table border="1">
        <thead>
            <tr>
                <th>품번</th>
                <th>상품명</th>
                <th>개수</th>
                <th>총금액</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${cartItems}">
                <tr>
                    <td>${item.productId}</td>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
                    <td>${item.quantity * item.price}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form action="/payment/save" method="post">
        <label>이름:</label> <input type="text" name="recipientName" value="${userName}" /><br />
        <label>주소:</label> <input type="text" name="recipientAddress" value="${userAddress}" /><br />
        <input type="submit" value="확인" />
    </form>
</body>
</html>
