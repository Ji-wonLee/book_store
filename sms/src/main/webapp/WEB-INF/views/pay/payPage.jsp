<%@ page import="sms.dto.CartDto" %>
<%@ page import="sms.dto.ProductDto" %>
<%@ page import="sms.dto.PaymentDto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>결제 진행</title>
    <script>
        function validateForm() {
            var name = document.getElementById("receiver_name").value;
            var address = document.getElementById("receiver_address").value;
            if (name === "" || address === "") {
                alert("이름과 주소는 필수 입력 사항입니다.");
                return false;
            }
            return true;
        }
    </script>
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
                    <td>${item.product_id}</td>
                    <td>${item.product_name}</td>
                    <td>${item.quantity}</td>
                    <td>${item.quantity * item.price}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
   <form action="/sms/payment/processPayment" method="post">
	    <input type="hidden" name="user_id" value="${user_id}" />
	    <label>수령인 이름:</label> <input type="text" name="receiver_name" required /><br />
	    <label>수령인 주소:</label> <input type="text" name="receiver_address" required /><br />
	    <input type="submit" value="계속" />
	</form>


</body>
</html>