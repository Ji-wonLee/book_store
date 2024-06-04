<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<title>회원 가입</title>
		<button onclick="history.back()" type="submit" >뒤로가기</button>
	</head>
	<p>${idChkSttStr}</p>
	<script type="text/javascript">
		function checkForm(){
			
			let f = document.joinForm;
				if (f.userPass.value != f.userPassCheck.value) {
				alert("비밀번호가 불일치");
				return false;				
			} else {
				return true;
			}
		}
	</script>
	<body>
		<c:set var ="stt" value="${idChkStt}"/>
		<c:if test = "${stt eq 1}">
			<script>
				alert('존재한 아이디');
			</script>
		</c:if>
		<form action="/sms/join.do" name="joinForm" onsubmit="return checkForm()" method="get">
			<table>
				<tr>
					<td style='text-align:left;width:150px'>아이디 :</td>
					
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="text" id="userId" name="userId" required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>비밀번호 :</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="password" id="userPass" name="userPass"required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>비밀번호 확인 :</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="password" id="userPassCheck" name="userPassCheck" required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>주소 :</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" id="userAddr" name="userAddr" required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>이름 :</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" id="userName" name="userName" required/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>전화번호 :</td>
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
	</body>
	
</html>