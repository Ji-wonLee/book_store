<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>금액 결제</title>
    <script>
        function validateForm() {
            var payerName = document.getElementsByName("payerName")[0].value;
            var payerAccount = document.getElementsByName("payerAccount")[0].value;
            if (payerName === "" || payerAccount === "") {
                alert("입금자 명과 입금자 계좌는 필수 입력 사항입니다.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h1>금액 결제</h1>
    <a href="/main">메인화면</a>
    <form action="/sms/payment/completePayment" method="post" onsubmit="return validateForm()">
        <label>입금자 명:</label> <input type="text" name="payerName" required /><br />
        <label>입금자 계좌:</label> <input type="text" name="payerAccount" required /><br />
        <label>결제 금액:</label> <span>${totalAmount}</span><br />
        <label>입금 계좌:</label> <span>000-0000-0000</span><br />
        <label>계좌 명:</label> <span>은행 이름</span><br />
        <input type="submit" value="확인" />
    </form>
</body>
</html>
