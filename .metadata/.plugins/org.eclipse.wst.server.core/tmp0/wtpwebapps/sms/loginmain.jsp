<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	
	<fieldset style='width:200px; margin-top:200px; margin-left:auto; margin-right:auto;'>
		<form action="login.jsp" method="post">
			<input type='text' id='id' name='id' placeholder='LOGIN' style='margin-top:10px'></input>
			<input type='password' id='pass' name='pass' placeholder='PASSWORD' style='margin-top:10px'></input>
			<input type='submit' id='lgnBtn' value='LOGIN' style='margin-top:10px;width:177px;height:21px;'></input>
			<input type='button' onclick="location.href='main.jsp'" value='BACK' style='margin-top:10px;width:177px;height:21px;'></input>
			<input type='button' onclick="location.href='join.jsp'" value='JOIN' style='margin-top:10px;width:177px;height:21px;'></input>
		</form>
	</fieldset>
	<%
		
		String fail = request.getParameter("fail");
	%>
	<c:choose>
    	<c:when test = "${fail eq 'f'}">
    		<script>
				alert('FAILED TO LOGIN');
			</script>
    	</c:when>
    	<c:otherwise>
    		<%fail = "";%>
    	</c:otherwise>
	</c:choose>
</head>
<body>
</body>
</html>