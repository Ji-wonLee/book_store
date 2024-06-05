<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<title>회원 가입</title>
		<button onclick="history.back()" type="submit">뒤로가기</button>
		</head>
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
	<body>
		<p>내 정보</p>
		<form action=<c:url value='/updateMyInfo'><c:param name="userId" value='${myInfoList[0].user_id}'/></c:url>
			 name="modifyForm" onsubmit="return checkForm()" method="get">
			<table>
				<tr>
					<td style='text-align:left;width:150px'>아이디 :</td>
					<td>
						<input type="text" style='border:none;' id="userId" name="userId" value='${myInfoList[0].user_id}'/>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>이름 :</td>
					<td>
						<input type="text" style='border:none;' value='${myInfoList[0].name}'/>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>비밀번호 :</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="password" id="userPass" name="userPass"/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>비밀번호 확인 :</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" type="password" id="passwordCheck" name="userPassCheck"/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>주소 :</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" id="userAddr" name="userAddr"/>
						</div>
					</td>
				</tr>
				<tr>
					<td style='text-align:left;width:150px'>전화번호 :</td>
					<td>
						<div class="form-group has-feedback">
							<input class="form-control" id="userCall" name="userCall"/>
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
	</body>
</html>