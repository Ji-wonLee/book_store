<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>발주서</title>
<script>
    function filterAndSubmitForm() {
        const form = document.getElementById('orderForm');
        const inputs = form.querySelectorAll('input[type="number"]');
        inputs.forEach(input => {
            if (input.value == '0') {
                input.name = '';
            }
        });
        form.submit();
    }
</script>
</head>
<body>
	 <form id="orderForm" action="/sms/orderCheck" method="post" onsubmit="filterAndSubmitForm(); return false;">
		<button type="submit">발주확인</button>
		<table border="1">
			<tr>
				<th>상품 id</th>
				<th>상품명</th>
				<th>가격</th>
				<th>회사명</th>
				<th>분류</th>
				<th>표지 url</th>
				<th>상태</th>
				<th>수량</th>
			</tr>
			<c:forEach var="product" items="${listProduct}">
				<tr>
					<td>${product.product_id}</td>
					<td>${product.product_name}</td>
					<td>${product.product_price}</td>
					<td>${product.manufacture_name}</td>
					<td>${product.category_name}</td>
					<td>${product.product_imgurl}</td>
					<td>${product.state}</td>
					<td><input type="number" name="${product.product_id}_${product.product_price}"
						value="0" min="0"></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>