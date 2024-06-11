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
<title>내 정보 보기</title>

	<script type="text/javascript">
		function checkForm(){
			
			let f = document.modifyForm;
			if (f.userPass.value == "" && f.userPassCheck.value != "") {
				alert("비밀번호 확인 입력 필수");
				return false;				
			} else if (f.userPass.value != "" && f.userPassCheck.value == "") {
				alert("비밀번호 확인");
				return false;				
			} else if (f.userPass.value != f.userPassCheck.value) {
				alert("비밀번호가 불일치");
				return false;				
			} else {
				return true;
			}
		}
		
		function submitData(){
			const id = get;
		}
	</script>
</head>
<body>
	<section>
		<nav>
		</nav>
		<article>
		<button onclick="history.back()" type="submit" >뒤로가기</button>
			<p>내 정보</p>
		<form action=<c:url value='/updateMyInfo'><c:param name="userId" value='${myInfoList[0].user_id}'/></c:url>
			 name="modifyForm" onsubmit="return checkForm()" method="get">
			<table style='height:40px'>
				<tr>
					<td style='text-align:left;width:150px'>아이디</td>
					<td >
						<input type="text" value='${myInfoList[0].user_id}' disabled readonly style="background-color: transparent;border:none"/>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>이름</td>
					<td>
						<input type="text" value='${myInfoList[0].name}' disabled readonly style="background-color: transparent;border:none"/>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>비밀번호</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="password" id="userPass" name="userPass" placeholder='${myInfoList[0].passwd}'/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>비밀번호 확인</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="password" id="passwordCheck" name="userPassCheck"/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>주소</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" id="userAddr" name="userAddr" placeholder='${myInfoList[0].address}'/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>전화번호</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" id="userCall" name="userCall" placeholder='${myInfoList[0].phonenum}'/>
						</div>
					</td>
				</tr>
				<tr>
					<td></td>
					<td style='text-align:center'>
						<div class="form-group has-feedback">
							<button class="btn btn-success" type="submit" id="save" style='width:177px'>저장</button>
						</div>
					</td>
				</tr>
			</table>
		</form>
		</article>
	</section>
	<!-- </form> -->
</body>
	<footer>
		<p> - 2024년도 kitri 보안개발 8기 포트폴리오 프로젝트 1팀 -</p>
	</footer>
</html>