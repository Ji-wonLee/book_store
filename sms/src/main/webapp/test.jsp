<%@ page import="sms.dto.CartDto"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%> <!-- 임시 데이터 생성용 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>결제진행</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
    crossorigin="anonymous">
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
<script
    src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>
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
</style>
</head>
<body>
    <div class="max-w-4xl mx-auto">
        <div class="header-container">
            <h3 style="margin-left: 60%;">{$user_name}님 환영합니다.</h3>
            <button type="button" class="btn btn-outline-dark">상품 조회</button>
        </div>

        <div class="flex items-center space-x-2 border-b p-4">
            <input type="checkbox" onclick="selectAll(this)"></input> <label for="selectAll"
                class="text-sm font-medium leading-none">전체선택</label>
        </div>

        <div>
            <c:forEach items="${cartItems}" var="item">
                <div class="border-b p-4 flex items-center justify-between">
                    <div class="flex items-center space-x-2">
                        <input type="checkbox"></input>
                        <img src="${item.imageUrl}" alt="Product Image" class="w-12 h-12 rounded">
                        <span class="text-sm font-medium leading-none">${item.product_name}</span>
                    </div>
                    <div class="space-x-2">
                        <span class="text-sm">정상가: ${item.price}원</span>
                        <span class="text-sm font-medium">판매가: ${item.discountPrice}원</span>
                    </div>
                    <div class="flex items-center space-x-2">
                        <input type="number" min="1" value="${item.quantity}"
                            style="border: 1px solid gray; width: 50px;"></input>
                        <span class="text-sm font-medium">${item.totalPrice}원</span>
                    </div>
                    <button
                        class="btn btn-danger btn-sm">삭제</button>
                </div>
            </c:forEach>
        </div>

        <div class="flex justify-between bg-[#f3f6f9] p-4">
            <div>
                <span class="text-sm">총 상품 가격</span>
                <span class="text-sm font-medium">${totalPrice}원</span>
            </div>
            <button type="button" class="btn btn-outline-dark"
                style="margin-top: 5%; float: right;">결제하기</button>
        </div>
    </div>

    <script>
        function selectAll(source) {
            checkboxes = document.querySelectorAll('input[type="checkbox"]');
            for(var i = 0; i < checkboxes.length; i++) {
                if(checkboxes[i] != source)
                    checkboxes[i].checked = source.checked;
            }
        }
    </script>
</body>
</html>