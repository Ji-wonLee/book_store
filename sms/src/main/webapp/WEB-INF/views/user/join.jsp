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
<title>회원 가입</title>
<c:set var ="stt" value="${idChkStt}"/>
<script type="text/javascript">
	function checkForm(){
		let f = document.joinForm;
		var mData = ${stt};
			if (f.userPass.value != f.userPassCheck.value) {
			alert("비밀번호가 불일치");
			return false;				
		} else if(mData == 0){
			alert("로그인 성공");
			return true;
		}
	}
</script>
</head>
<body>
	<section>
		<nav>
		</nav>
		<article>
		<c:if test = "${stt eq 1}">
			<script>
				alert('존재한 아이디');
			</script>
		</c:if>
		<button onclick="history.back()" type="submit" >뒤로가기</button>
		<form action="/sms/join.do" name="joinForm" onsubmit="return checkForm()" method="get">
			<table>
				<tr>
					<td style='text-align:left;width:150px'>아이디</td>
					
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="text" id="userId" name="userId" required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>비밀번호</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="password" id="userPass" name="userPass"required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>비밀번호 확인</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="password" id="userPassCheck" name="userPassCheck" required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>주소</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" id="userAddr" name="userAddr" required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>이름</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" id="userName" name="userName" required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>전화번호</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" id="userCall" name="userCall" required/>
						</div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td style='text-align:center'>
						<div class="form-group has-feedback">
							<button class="btn btn-success" type="submit" id="join" style='width:177px'>회원가입</button>
						</div>
					</td>
				</tr>
			</table>
		</form>
		</article>
	</section>
</body>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</html>