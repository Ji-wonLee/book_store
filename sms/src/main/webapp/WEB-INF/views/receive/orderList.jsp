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
            background: #ffd9df;
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
        
		.input {
        	font-family: "Noto Sans KR", sans-serif;
 			font-optical-sizing: auto;
 			font-weight: 500;
 			font-style: normal;
        	padding: 7px;
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

@media ( max-width : 600px) {
	nav, article {
		width: 100%;
		height: auto;
	}
}
</style>
<title>입고 관리 화면</title>
</head>
<body>
<div class="header-container">
        <h3>관리자 ${user_id} 님 환영합니다.</h3>
        <a href="/sms/toAdminMain"><img style="height:100px; width:auto;" src="resources/logo_01.png" alt="logo" width=auto height="100px"/></a>
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
			<form id="order_id" action="/sms/updateReceive" method="get">
			<div class="input">
				<button style="border-radius: 3px; width: 150px; height: 40px; background-color : #f7e1ea;" type="submit">발주선택</button>
			</div>
				<table>
					<tr>
						<th></th>
						<th>발주ID</th>
						<th>발주날짜</th>
						<th>작성자</th>
						<th>총결제액</th>
						<th>상태</th>
					</tr>
					<c:forEach var="order" items="${orderIdList}">
						<tr>
							<td><input type="radio" name="order_id"
								value="${order.order_id}" required><br></td>
							<td>${order.order_id}</td>
							<td>${order.order_date}</td>
							<td>${order.writer}</td>
							<td>${order.totalprice}</td>
							<td>${order.state}</td>
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