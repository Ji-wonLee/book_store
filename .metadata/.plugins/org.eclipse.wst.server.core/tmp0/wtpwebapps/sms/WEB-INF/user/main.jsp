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
<title>BOOK</title>
	<c:set var="userLoginStt" value="${userLoginStt}"/>
	<c:set var="userClientStt" value="${userClientStt}"/>
		
	<fieldset style='width:160px; margin-top:200px; margin-left:10%;'>
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
									<td>
										<input style='width:69.33px' type='submit' value='상품' name='lnkDt' id='order'></input>
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
</head>
<body>
	<fieldset style='width:160px;margin-top:-117px;margin-left:31%;'>
		<table border= '1'>
			<tr>
				<td>${user_id}</td>
				<td>${userClientStt}</td>
			</tr>
		</table>
	</fieldset>
</body>
<footer style='bottom:0px;position:absolute;height:5rem;background:black;width:100%;margin-left:-10px;text-align:center;'>
	<p style='color:white;font-size:8px;'>- 2024 보안개발 8기 프로젝트 1팀 -</p>
</footer>
</html>