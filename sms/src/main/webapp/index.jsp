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
	
	@font-face {
		font-family: "NotoSansKR";
		src:url("resources/Noto_Sans_KR/NotoSansKR-VariableFont_wght.ttf");
	}
	
	* {
 		box-sizing: border-box;
		font-family:'NotoSansKR'
	}

	article {
		overflow:auto;
		float: left;
		padding: 20px;
 		width: 100%;
 		height: 1000px;
 		background-color: #f2f2f2;
 		display:flex;
 		flex-direction: column;
 		justify-content:center;
 		align-items:center;
	}
	
	article table {
		border-collapse: collapse;
		width : 100%';
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
	<section>
		<article>
			<script>
				function getParams(){
			    	var url = window.location.search.replace('?','');
			    	var params = {};
			    	var urlPrm = url.split('=')[1];
			    	if(urlPrm.trim() == "fail"){
			    		alert('FAILED TO LOGIN');
			    	}
			    	return urlPrm;
				}
				const params = getParams();
				
				console.log(params);
			</script>
			<div>
				<a href="#" onClick="history.go(0)">
					<img src="resources/logo_01.png" height="200"/>
				</a>
			</div>
			<br>
			<div style='background-color:#c2c2d6;' >
				<form action="/sms/mainLgn.do" method="get" style='width:165px;'>
					<table style='border:none;'>
						<tr>
							<td><input type='text' id='userId' name='userId' placeholder='LOGIN' style='margin-top:10px;width:100px;' required></input></td>
							<td rowspan="2"><input type='submit' id='lgnBtn' value='LOGIN' style='margin-top:10px;width:58px;height:66px;display:flex;justify-content:center;background-color:#f2f2f2;border:none' ></input></td>
						</tr>
						<tr>
							<td><input type='password' id='userPass' name='userPass' placeholder='PASSWORD' style='margin-top:10px;width:100px;' required></input></td>
						</tr>
						<tr>
							<td><a href="/sms/toJoin" style='font-size:10px'>회원가입</a></td>				
						</tr>
					</table>
				</form>
			</div>
			<div>
				<h5>저렴하고 빠른 서점 온라인 사이트</h5>
			</div>
		</article>
	</section>
	<!-- </form> -->
</body>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</html>