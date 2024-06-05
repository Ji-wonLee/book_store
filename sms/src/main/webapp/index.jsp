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
</head>
<body>
	<!-- <form action = "/sms/searchList" method = "GET"> --> <!-- action 연결 필요 -->
	<section>
		<nav>
		 <a href="http://localhost:8080/sms/list">Go to Cart</a>
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
						<td><a href="http://localhost:8080/sms/toJoin" style='font-size:10px'>회원가입</a></td>				
					</tr>
				</table>
			</form>
		</nav>
		<article>
			
		</article>
	</section>
	<!-- </form> -->
</body>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</html>