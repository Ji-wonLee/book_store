<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Index Page</title>
</head>
<body>
<h1>Welcome to the Bookstore</h1>

<form action = "/sms/customermain" method = "get">
<input type="submit" value="User">
</form>

<form action = "/sms/inventory" method = "get">
<input type="submit" value="Admin">
</form>

<a href="http://192.168.10.150:9000/sms/list">Go to Cart</a>

</body>
</html>
