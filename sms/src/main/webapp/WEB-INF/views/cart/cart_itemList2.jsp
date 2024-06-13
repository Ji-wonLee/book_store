<%@ page import="java.util.List"%>
<%@ page import="sms.dto.CartDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>장바구니</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
        @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR&display=swap');
        
        .header-container {
            font-family: "Noto Sans KR", sans-serif;
            font-optical-sizing: auto;
            font-weight: 500;
            font-style: normal;
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
            padding: 0 20px;
        }

        .header-container h3 {
            margin: 0;
        }

        nav {
            font-family: "Noto Sans KR", sans-serif;
            font-optical-sizing: auto;
            font-weight: 500;
            font-style: normal;
            width: 100%;
            background: #c2c2d6;
            padding: 10px 0;
        }

        nav ul {
            display: flex;
            justify-content: space-around;
            list-style: none;
            padding: 0;
            margin: 0;
        }

        nav ul li {
            margin: 0;
        }

        nav ul li a {
            text-decoration: none;
            color: black;
        }

        .search {
            font-family: "Noto Sans KR", sans-serif;
            font-optical-sizing: auto;
            font-weight: 500;
            font-style: normal;
            border: 2px solid purple;
            border-radius: 30px;
            background-color: #eaeafb;
            text-align: center;
            line-height: 10px;
            margin-top: 22px;
            height: 35px;
            width: 900px;
        }

        footer {
            font-family: "Noto Sans KR", sans-serif;
            font-optical-sizing: auto;
            font-weight: 500;
            font-style: normal;
            padding: 10px;
            text-align: center;
            color: #8c8c8c;
        }

        .table-container {
            font-family: "Hahmlet", serif;
            font-optical-sizing: auto;
            font-weight: 400;
            font-style: normal;
            margin-top: 20px;
        }

        table {
            font-family: "Hahmlet", serif;
            font-optical-sizing: auto;
            font-weight: 400;
            font-style: normal;
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border-bottom: 1px solid #ddd;
            padding: 8px;
        }

        th {
            text-align: center;
            background-color: #f2f2f2;
            text-align: left;
        }

        td img {
            text-align: left;
            max-width: 50px;
            height: auto;
        }

        .btn-delete {
            color: #dc3545;
            cursor: pointer;
        }

        .btn-delete:hover {
            text-decoration: underline;
        }

        .flex {
            display: flex;
            justify-content: space-between;
        }

        .space-y-1 > div {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <div class="header-container">
        <h3>${user_id}님 환영합니다.</h3>
        <img style="height:100px; width:auto;" src="resources/logo_01.png" alt="logo" width=auto height="100px"/>
        <button type="button" onclick="location.href='index.jsp'">로그아웃</button>
    </div>
    <nav>
        <ul>
            <li><a href="/sms/myInfo">내 정보 조회</a></li>
            <li><a href="/sms/list">장바구니</a></li>
            <li><a href="/sms/customermain">상품 조회</a></li>
        </ul>
    </nav>
    <section>
        <div class="max-w-4xl mx-auto">
            <form id="cartForm" action="/sms/payment/checkStock" method="post">
                <div class="table-container">
                    <table class="table">
                        <thead>
                            <tr>
                                <th><input type="checkbox" id="selectAll" onclick="toggleAll(this)"></th>
                                <th>이미지</th>
                                <th>상품명</th>
                                <th>정상가</th>
                                <th>판매가</th>
                                <th>수량</th>
                                <th>소계</th>
                                <th>삭제</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cartItems}">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="cartItemIds" value="${item.product_id}">
                                        <input type="hidden" name="price_${item.product_id}" value="${item.product_price}">
                                        <input type="hidden" name="quantity_${item.product_id}" value="${item.quantity}">
                                    </td>
                                    <td><img src="${item.product_imgurl}" alt="Product Image"></td>
                                    <td>${item.product_name}</td>
                                    <td>${item.product_price} 원</td>
                                    <td>${item.product_price} 원</td>
                                    <td>
                                        <input type="number" name="quantity_${item.product_id}" min="1" value="${item.quantity}" style="border: 1px solid gray; width: 50px;" onchange="updateItemQuantity('${item.product_id}', this.value)">
                                    </td>
                                    <td>${item.product_price * item.quantity} 원</td>
                                    <td><a href="#" class="btn-delete" onclick="deleteCartItem('${item.product_id}')">삭제</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="flex justify-between bg-[#f3f6f9] p-4">
                    <div class="space-y-1">
                        <div class="flex justify-between">
                            <span class="text-sm">총 상품 가격</span>
                            <span class="text-sm font-medium" id="totalPrice">${totalPrice} 원</span>
                        </div>
                        <div class="flex justify-between">
                            <span class="text-sm">배송비</span>
                            <span class="text-sm font-medium">0 원</span>
                        </div>
                        <div class="flex justify-between">
                            <span class="text-sm">총 주문 상품수</span>
                            <span class="text-sm font-medium" id="totalQuantity">${totalQuantity} 건</span>
                        </div>
                    </div>
                    <div class="space-y-1">
                        <div class="flex justify-between">
                            <span class="text-sm">총 결제 예정 금액</span>
                            <span class="text-sm font-medium" id="totalPayment">${totalPrice} 원</span>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-outline-dark" style="margin-top: 5%; float: right;">결제하기</button>
            </form>
        </div>
    </section>

    <script>
        function toggleAll(source) {
            const checkboxes = document.querySelectorAll('input[name="cartItemIds"]');
            checkboxes.forEach(checkbox => checkbox.checked = source.checked);
        }

        function deleteCartItem(productId) {
            if (confirm("삭제하시겠습니까?")) {
                $.ajax({
                    url: '/sms/cart/deleteItem',
                    type: 'POST',
                    data: { product_id: productId },
                    success: function(response) {
                        alert(response);
                        location.reload(); // 페이지를 새로고침하여 삭제된 항목 반영
                    },
                    error: function(xhr, status, error) {
                        alert("삭제에 실패했습니다: " + error);
                    }
                });
            }
        }

        function updateItemQuantity(productId, quantity) {
            $.ajax({
                url: '/sms/cart/updateItemQuantity',
                type: 'POST',
                data: { product_id: productId, quantity: quantity },
                success: function(response) {
                    updateCartSummary();
                },
                error: function(xhr, status, error) {
                    alert("수량 업데이트에 실패했습니다: " + error);
                }
            });
        }

        function updateCartSummary() {
            let totalPrice = 0;
            let totalQuantity = 0;
            $('input[name^="quantity_"]').each(function() {
                const productId = $(this).attr('name').split('_')[1];
                const quantity = parseInt($(this).val());
                const price = parseInt($(`input[name="price_${productId}"]`).val());
                totalPrice += price * quantity;
                totalQuantity += quantity;
            });
            $('#totalPrice').text(totalPrice + ' 원');
            $('#totalQuantity').text(totalQuantity + ' 건');
            $('#totalPayment').text(totalPrice + ' 원');
        }
    </script>

    <footer>
        <p>- 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
    </footer>
</body>
</html>
