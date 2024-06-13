<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="oracle.jdbc.*"%>
<%@ page import="oracle.jdbc.pool.OracleDataSource"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "java.util.HashMap"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"> <!-- 추가할부분 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- 추가할부분 -->
	<meta http-equiv="X-UA-Compatible" content="ie=edge"> <!-- 추가할부분 -->
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
		overflow:auto;
		float: left;
		padding: 20px;
 		width: 80%;
 		height: 1000px;
 		background-color: #f2f2f2;
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
<title>상품 검색</title>

</head>
<body>
	<!-- <form action = "/sms/searchList" method = "GET"> --> <!-- action 연결 필요 -->
	<section>
		<nav>
			<c:set var="userLoginStt" value="${userLoginStt}"/>
			<c:set var="userClientStt" value="${userClientStt}"/>
			<fieldset style='width:160px;border:none;margin-left:-20px;margin-top:-20px;'>
				<c:choose>
					<c:when test ="${userLoginStt eq 'success'}">
						<c:choose>
							<c:when test="${userClientStt eq 'admin'}">
								${userName}님, 어서오세요<br>
								<a href="javascript:window.history.back();">로그아웃</a>
								<form action="/sms/admStt" method="get">
									<table>
										<tr>
											<td>
												<input style='width:69.33px' type='submit' value='입고' name='lnkDt' id='receive'></input>
											</td>
											<td>
												<input style='width:69.33px' type='submit' value='발주' name='lnkDt' id='order'></input>
											</td>

										</tr>
										<tr>
											<td>
												<input style='width:69.33px' type='submit' value='사용자' name='lnkDt' id='receive'></input>
											</td>
											<td>
												<input style='width:69.33px' type='submit' value='재고' name='lnkDt' id='order'></input>
											</td>
										</tr>
										<tr>
											<td>
												<input style='width:69.33px' type='submit' value='상품' name='lnkDt' id='order'></input>
											</td>
										</tr>						
									</table>						
								</form>
							</c:when>
						<c:otherwise>
							${userName}님, 어서오세요<br>
							<a href="javascript:window.history.back();">로그아웃</a>
							<a href="cart?lnkDt=cart">장바구니</a><br>
							<a href="<c:url value='myInfo?lnkDt=myInfo'><c:param name="userId" value="${user_id}"/></c:url>">내정보</a>
						</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<form action="/sms/mainLgn.do" method="get">
							<table style='border:none;'>
								<tr>
									<td><input type='text' id='userId' name='userId' placeholder='LOGIN' style='margin-top:10px;width:100px;' required></input></td>
									<td rowspan="2"><input type='submit' id='lgnBtn' value='LOGIN' style='margin-top:10px;width:50px;height:50px;display:flex;justify-content:center;' ></input></td>
								</tr>
								<tr>
									<td><input type='password' id='userPass' name='userPass' placeholder='PASSWORD' style='margin-top:10px;width:100px' required></input></td>
								</tr>
								<tr>
									<td><a href='join?lnkDt=join' style='font-size:10px'>회원가입</a></td>				
								</tr>
							</table>
						</form>
					</c:otherwise>
				</c:choose>
			</fieldset>
		</nav>
		<article>
			<div class = "searchBox"> <!-- 상단 검색 구성 -->
				<select name = "category" id = "categoryId"> <!-- 분류 드롭 다움 메뉴 -->
					<c:forEach var="category" items="${categoryList}" >
						<option value = "${category.id}">${category.name}</option>
					</c:forEach>
				</select>
				<input type = "text" id = "searchText" placeholder="검색어 입력"/> <!-- 검색어 입력 text 박스 -->
				<input type = "submit" value="검색"/> <!-- 분류, 검색어 송신 버튼 -->
			</div>
	
			<div class = "productlist"> <!-- 상품 출력을 위한 테이블 -->
				<table>
					<tr>
						<th> </th>
						<th>분류</th>
						<th>품명</th>
						<th>가격</th>
						<th>페이지</th>
					</tr>
					<c:forEach var="product" items="${productList}" varStatus="idx">
						<tr> 
							<td>${idx.count}</td>
							<td>${product.categoryName}</td>
							<td>${product.name}</td>
							<td>${product.price}</td>
							<td>${product.page}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</article>
	</section>
	<!-- </form> -->
</body>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</html>