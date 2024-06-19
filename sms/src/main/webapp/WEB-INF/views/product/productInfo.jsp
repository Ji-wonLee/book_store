<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
    @import url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Hahmlet:wght@100..900&family=Noto+Sans+KR&display=swap');

	* {
 		 box-sizing: border-box;
	}

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
        
		.productImage {
		 padding: 7px;
		}
        
		.productInfo {
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
   
        }

        td img {
        	text-align: left;
            max-width: 50px;
            height: auto;
        }
        
        .input {
        	font-family: "Noto Sans KR", sans-serif;
 			font-optical-sizing: auto;
 			font-weight: 500;
 			font-style: normal;
        	padding: 7px;
        }
	
		section::after {
		content: "";
  		display: table;
  		clear: both;
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
	
	@media (max-width: 600px){
  		nav, article {
   			 width: 100%;
    		height: auto;
 		 }
	}
</style>
	<script>
	function checkNum() {
        var num = "${number}";
        var quant = "${quantity}";
        if (parseInt(num) <= parseInt(quant)) {
            document.getElementById("addToCartForm").submit();
        } else {
            alert("죄송합니다. 상품의 재고량을 넘어가는 값을 입력하셨습니다. 입고를 기다려 주십시오.");
            history.back();
        }
    }
    
	function getCart(event) {
	    event.preventDefault(); // 기본 제출 동작을 막음
	    var state = "${state}";
	    if (state == "품절") {
	        alert("품절 상품 입니다.");
	        history.back();
	    } else if (state == "임시품절") {
	        alert("임시 품절 상품 입니다.");
	        history.back();
	    } else {
	        var userConfirmation = confirm("장바구니로 바로 이동하시겠습니까?");
	        var redirectValue = userConfirmation ? "true" : "false";
	        var form = document.getElementById("stateCheck");
	        form.action = "/sms/addItemtoCart";
	        var input = document.createElement("input");
	        input.type = "hidden";
	        input.name = "redirect";
	        input.value = redirectValue;
	        form.appendChild(input);
	        form.submit();
	    }
	}

	</script>
<title>상품 정보</title>
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
		<form id="stateCheck" action = "/sms/addItemtoCart" method = "GET"> <!-- action 연결 필요 -->
			<div class="productInfo">
    			<div class="productImage">
        			<img style="border: 1px solid black; border-radius: 3px; box-shadow: 5px 5px 3px #aaaaaa" src="${product_imgurl}" alt="Product Image">
    			</div>
    			<div class="productDetails">
        			<table>
            			<tr>
                			<th>도서명</th>
               			 	<td>${product_name}</td>
            			</tr>
            			<tr>
                			<th>분류</th>
                			<td>${category_name}</td>
            			</tr>
			            <tr>
			                <th>페이지 수</th>
			                <td>${product_page}</td>
			            </tr>
			            <tr>
			                <th>설명</th>
			                <td>${description}</td>
			            </tr>
			            <tr>
			                <th>제조업체 명</th>
			                <td>${manufacture_name}</td>
			            </tr>
			            <tr>
			                <th>가격</th>
			                <td>${product_price}</td>
			            </tr>
			            <tr>
			                <th>판매현황</th>
			                <td>${state}</td>
			            </tr>
			            <tr>
			            	<th>구매 수량</th>
			            	<td><input type="number" name="number" value="${number}"></td>
			            </tr>
			        </table>
    			</div>
    			
 				<input type="hidden" name="product_id" value="${product_id}">
 				<input type="hidden" name="product_price" value="${product_price}">
 				<input type="hidden" name="product_name" value="${product_name}">
 				<div class="input">
    				<input style="border-radius: 3px; width: 150px; height: 40px; background-color : #c2c2d6;" type="submit" id="addCart" value="장바구니 추가" onclick="getCart(); event.preventDefault();">
    			</div>
			</div>
		</form>
		</div>
	</section>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>