<%@ page import="java.util.List"%>
<%@ page import="sms.dto.PaymentDetailDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>결제 페이지</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<style>
.header-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
    padding: 0 20px;
}

.header-container h3 {
    margin: 0;
}

footer {
    padding: 10px;
    text-align: center;
    color: #8c8c8c;
}

.table-container {
    margin-top: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
}

th {
    background-color: #f2f2f2;
    text-align: left;
}

td img {
    max-width: 50px;
    height: auto;
}
</style>
</head>
<body>
    <div class="max-w-4xl mx-auto">
        <div class="header-container">
            <h3>${user_id}님 환영합니다.</h3>
            <button type="button" onclick="location.href='/sms/customermain'">상품 조회</button>
        </div>
        <section>
            <form id="paymentForm" action="/sms/payment/processPayment" method="post">
                <div class="table-container">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>이미지</th>
                                <th>상품명</th>
                                <th>정상가</th>
                                <th>판매가</th>
                                <th>수량</th>
                                <th>소계</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cartItems}">
                                <tr>
                                    <td><img src="${item.product_imgurl}" alt="Product Image"></td>
                                    <td>${item.product_name}</td>
                                    <td>${item.product_price} 원</td>
                                    <td>${item.product_price} 원</td>
                                    <td>${item.quantity}</td>
                                    <td>${item.product_price * item.quantity} 원</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="flex justify-between bg-[#f3f6f9] p-4">
                    <div class="space-y-1">
                        <div class="flex justify-between">
                            <span class="text-sm">총 상품 가격</span>
                            <span class="text-sm font-medium">${totalPrice} 원</span>
                        </div>
                        <div class="flex justify-between">
                            <span class="text-sm">배송비</span>
                            <span class="text-sm font-medium">0 원</span>
                        </div>
                        <div class="flex justify-between">
                            <span class="text-sm">총 주문 상품수</span>
                            <span class="text-sm font-medium">${totalQuantity} 건</span>
                        </div>
                    </div>
                    <div class="space-y-1">
                        <div class="flex justify-between">
                            <span class="text-sm">총 결제 예정 금액</span>
                            <span class="text-sm font-medium">${totalPrice} 원</span>
                        </div>
                    </div>
                </div>
                <div>
                    <label>수령인 이름:</label>
                    <input type="text" name="receiver_name" required />
                    <label>수령인 주소:</label>
                    <input type="text" name="receiver_address" required />
                </div>
                <button type="submit" class="btn btn-outline-dark" style="margin-top: 5%; float: right;">결제하기</button>
            </form>
        </section>
    </div>
    <footer>
        <p>- 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
    </footer>
</body>
</html>