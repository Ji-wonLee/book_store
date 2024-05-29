<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>금액 결제</title>
</head>
<body>
    <h1>금액 결제</h1>
    <a href="/main">메인화면</a>
    <form action="/payment/updateStateToCompleted" method="post">
        <label>입금자 명:</label> <input type="text" name="payerName" /><br />
        <label>입금자 계좌:</label> <input type="text" name="payerAccount" /><br />
        <label>결제 금액:</label> <span>${totalAmount}</span><br />
        <label>입금 계좌:</label> <span>000-0000-0000</span><br />
        <label>계좌 명:</label> <span>은행 이름</span><br />
        <input type="submit" value="확인" />
    </form>
</body>
</html>
