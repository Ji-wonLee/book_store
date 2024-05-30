<%@page import="sms.dto.Category"%>
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
	
	section {
    display: flex;
    flex-direction: row;
    align-items: stretch;
	}

	nav {
		float: left;
  		width: 20%;
  		background: #9ec7ae;
 		padding: 20px;
	}
	
	article {
		float: left;
		padding: 20px;
 		width: 80%;
 		background-color: #dbf0e6;
	}
	
	article table {
		border-collapse: collapse;
		width : 100%';
	}
	
	article th {
		border-bottom: 1px solid #dddddd;
  		text-align: center;
  		padding: 8px;
	}
	
	article td {
		border-bottom: 1px solid #dddddd;
  		text-align: left;
  		padding: 8px;
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
<title>재고 관리 화면</title>
</head>
<body>
	<section>
		<nav id="nav">
			<!-- 좌측 유저정보 및 사이트 목록 표시 -->
				<div class = "userText">
					<h3>관리자 </h3>
					<h3>님 환영합니다. </h3>
				</div>
				<ul>
     				<li><a href="http://localhost:8080/sms">사용자 관리</a></li>
					<li><a href="http://localhost:8080/sms">재고 관리</a></li>
					<li><a href="http://localhost:8080/sms">발주 관리</a></li>
					<li><a href="http://localhost:8080/sms">입고 관리</a></li>
   				</ul>
		</nav>
		<article id="article">
			<form action="/sms/inventorysearch" method="get">
				<div class = "searchwithText">
					<input type="text" name="searchtext" id="searchText" placeholder="검색어 입력"/> <!-- 검색어 입력 text 박스 -->
					<input type="submit" value="검색"/>
				</div>
				<div class = "printInventoryList">
					<table>
						<tr>
							<th></th>
							<th>상품 코드</th>
							<th>도서명</th>
							<th>분류 코드</th>
							<th>분류명</th>
							<th>제조업체명</th>
							<th>제조업체 주소</th>
							<th>페이지</th>
							<th>가격</th>
							<th>판매 상태</th>
							<th>재고</th>
						</tr>
						<c:forEach var="inventory" items="${inventorylist}" varStatus="idx">
							<tr>
								<td>${idx.count}</td>
								<td>${inventory.product_id}</td>
								<td>${inventory.product_name}</td>
								<td>${inventory.category_id}</td>
								<td>${inventory.category_name}</td>
								<td>${inventory.manufacture_name}</td>
								<td>${inventory.manufacture_address}</td>
								<td>${inventory.product_page}</td>
								<td>${inventory.product_price}</td>
								<td>${inventory.state}</td>
								<td><input type="number" value="${inventory.quantity}"></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</form>
		</article>
	</section>
	<script>
        <!-- 화면 크기가 변경될 때마다 높이를 조정-->
        window.addEventListener('resize', function() {
            var section = document.getElementById('section');
            var article = document.getElementById('article');
            section.style.height = article.offsetHeight + 'px';
        });

        <!-- 초기 로드 시에도 높이를 조정-->
        window.dispatchEvent(new Event('resize'));
    </script>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>