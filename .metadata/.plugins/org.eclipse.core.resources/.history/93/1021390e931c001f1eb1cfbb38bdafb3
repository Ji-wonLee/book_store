<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<button onclick="history.back()" type="submit" >뒤로가기</button>
	<title>회원 관리</title>
</head>

<body>

<table border='1' style='margin-top:10px'>
	<tr>
		<th>사용자아이디</th>
		<th>사용자명</th>
		<th>사용자주소</th>
		<th>사용자등급</th>	
	</tr>
	<c:forEach var = "t" items="${userList}">
		<tr>
			<td>${t.user_id}</td>
			<td>${t.name}</td>
			<td>${t.address}</td>
			<td>${t.gname}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>