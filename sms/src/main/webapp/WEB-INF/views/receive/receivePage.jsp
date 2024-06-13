<%@page import="sms.dto.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        
		table {
			text-align: center;
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

		.input {
        	font-family: "Noto Sans KR", sans-serif;
 			font-optical-sizing: auto;
 			font-weight: 500;
 			font-style: normal;
        	padding: 7px;
        }
       	.insert{
       		font-family: "Noto Sans KR", sans-serif;
 			font-optical-sizing: auto;
 			font-weight: 500;
 			font-style: normal;
       	}

section::after {
	content: "";
	display: table;
	clear: both;
}

footer {
	padding: 10px;
	text-align: center;
	color: #8c8c8c;
}

@media ( max-width : 600px) {
	nav, article {
		width: 100%;
		height: auto;
	}
}
</style>
    <script>
	function goMenu(){
		alert("재고가 반영되었습니다. 메인화면으로 돌아갑니다.");
	}
</script>
<title>재고 관리 화면</title>
</head>
<body>
	<div class="header-container">
        <h3>관리자 ${user_id} 님 환영합니다.</h3>
        <img style="height:100px; width:auto;" src="resources/logo_01.png" alt="logo" width=auto height="100px"/>
        <button type="button" onclick="location.href='index.jsp'">로그아웃</button>
    </div>
    <nav>
        <ul>
            <li><a href="/sms/toAdminMain">메인화면</a></li>
        </ul>
    </nav>
	<section>
		<div class="max-w-4xl mx-auto">
			<!-- 여기에 출력할 코드를 작성(list라던가) -->
			<form action="/sms/toInventory" method="get">
			<div class="input">
				<button style="border-radius: 3px; width: 150px; height: 40px; background-color : #c2c2d6;" type="submit" onclick="goMenu();">재고반영</button>
			</div>
				<div class="insert">
					<p style="margin:'3px';">${rdList[0].receive_id}</p><br> 
					<input type="hidden"
						name="receive_id" value="${rdList[0].receive_id}"> 
					<p>작성자 : <input style="background-color: #eaeafb;" type="text" name="writer" id="writer" value="${writer}" />
					   결제자 : <input style="background-color: #eaeafb;" type="text" name="payer" id="payer" value="${payer}" /></p> 
				</div>
				<br>
				<table>
					<tr>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
						<th>수정수량</th>
					</tr>
					<c:forEach var="receiveDetail" items="${rdList}">
						<tr>
							<td>${receiveDetail.product_id}</td>
							<td>${receiveDetail.price}</td>
							<td>${receiveDetail.quantity}</td>
							<td><input style="background-color: #eaeafb;" type="number" name="${receiveDetail.product_id}"
								value="${receiveDetail.quantity}"></td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	</section>
	<footer>
		<p>- 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</body>
</html>