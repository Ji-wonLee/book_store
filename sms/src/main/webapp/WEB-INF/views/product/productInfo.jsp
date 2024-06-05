<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
	* {
 		 box-sizing: border-box;
	}

	nav {
		float: left;
  		width: 20%;
  		height: 1000px;
  		background: #c2c2d6;
 		padding: 20px;
	}
	
	article {
		float: left;
		padding: 20px;
 		width: 80%;
 		height: 1000px;
 		background-color: #f2f2f2;
	}
	
	article table {
		border-collapse: separate;
		width : 100%';
	}
	
	article th {
		border-bottom: 1px solid #dddddd;
  		text-align: center;
  		padding: 8px;
  		background-color: #ccc4d4;
	}
	
	article td {
		border-bottom: 1px solid #dddddd;
  		text-align: left;
  		padding: 8px;
  		background-color: white;
	}
	
	section::after {
		content: "";
  		display: table;
  		clear: both;
	}
	
	footer {
		padding: 10px;
 		text-align: center;
 		color : #8c8c8c;
	}
	
	@media (max-width: 600px){
  		nav, article {
   			 width: 100%;
    		height: auto;
 		 }
	}
</style>
	<script>
    function checkNum(var num, var quant){
        if(parseInt(num) <= quant){
            document.getElementById("addToCartForm").submit();
        } else {
            alert("죄송합니다. 상품의 재고량을 넘어가는 값을 입력하셨습니다. 입고를 기다려 주십시오.");
            history.back();
        }
    }
    
    function getCart(var num, var quant){
        var state = "${state}";
        if(state == "품절") {
            alert("품절 상품 입니다.");
            history.back();
        } else if(state == "임시품절") {
            alert("임시 품절 상품 입니다.");
            history.back();
        } else {
            checkNum(var num, var quant);
        }
    }
	</script>
<title>상품 정보</title>
</head>
<body>
	<section>
		<nav>
			<!-- 좌측 유저정보 및 사이트 목록 표시 -->
				<div class = "userText">
				<!-- session 과 사용자 정보를 가져옴, 이름 출력 -->
				<h3>${user_id} 님 환영합니다. </h3>
				</div>
				
				<ul>
     				<li><a href="http://localhost:8080/sms/">내 정보 조회</a></li>
					<li><a href="http://localhost:8080/sms/list">장바구니</a></li>
					<li><a href="http://localhost:8080/sms/customermain">상품 조회</a></li>
   				</ul>
		</nav>
		<article>
		<form id="stateCheck" action = "/sms/addItemtoCart" method = "GET"> <!-- action 연결 필요 -->
			<div class="productInfo">
    			<div class="productImage">
        			<img src="${product_imgurl}" alt="Product Image">
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
    			<input type="submit" id="addCart" value="장바구니 추가" onclick="getCart(${number}, ${quantity}); return false;">
				
			</div>
		</form>
		</article>
	</section>
	<script>
        // 화면 크기가 변경될 때마다 높이를 조정
        window.addEventListener('resize', function() {
            var section = document.getElementById('section');
            var article = document.getElementById('article');
            section.style.height = article.offsetHeight + 'px';
        });

        // 초기 로드 시에도 높이를 조정
        window.dispatchEvent(new Event('resize'));
    </script>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>