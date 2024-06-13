<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>금액 결제</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript">
    window.onpopstate = function(event) {
        updateCartStateToCart();
    };

    function goToProductMain() {
        updateCartStateToCart();
        window.location.href = '/sms/customermain'; // 컨트롤러 URL로 변경
    }

    function updateCartStateToCart() {
        var cartId = '<%= session.getAttribute("cart_id") %>';
        fetch('/sms/cart/revertState', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ cart_id: cartId })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                console.log("Cart state reverted to '장바구니'.");
            } else {
                console.error("Failed to revert cart state.");
            }
        });
    }

    function validateForm() {
        var payerName = document.getElementsByName("payer_name")[0].value;
        var payerAccount = document.getElementsByName("payer_account")[0].value;
        if (payerName === "" || payerAccount === "") {
            alert("입금자 명과 입금자 계좌는 필수 입력 사항입니다.");
            return false;
        }
        return true;
    }

    window.addEventListener("beforeunload", function(event) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/sms/payment/revertPayment", false);
        xhr.send();
    });
</script>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
    body {
        font-family: 'Noto Sans KR', sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 0;
    }

    .header-container {
        font-family: "Noto Sans KR", sans-serif;
        font-optical-sizing: auto;
        font-weight: 500;
        font-style: normal;
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
        padding: 20px;
        background-color: #c2c2d6;
    }

    .header-container h3 {
        margin: 0;
    }

    .container {
        max-width: 800px;
        margin: 50px auto;
        padding: 20px;
        background-color: #ffffff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
    }
	
	
    .header-container img {
        height: 100px; /* 명확한 높이 설정 */
        width: auto;  /* 자동 너비 조정 */
    }
    
    h1 {
        text-align: center;
        margin-bottom: 30px;
    }

    label {
        font-weight: bold;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-control {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
    }

    .btn-submit {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        margin-top: 20px;
    }

    .btn-submit:hover {
        background-color: #45a049;
    }

    .info {
        margin-bottom: 20px;
    }
</style>
</head>
<body>
    <div class="header-container">
        <h3>${user_id}님 환영합니다.</h3>
        <img src="${pageContext.request.contextPath}/resources/logo_01.png" alt="logo" />
        <button type="button" onclick="location.href='index.jsp'">로그아웃</button>
    </div>
    <div class="container">
        <h1>금액 결제</h1>
      
        <form action="/sms/payment/completePayment" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="user_id" value="${user_id}" />
            <input type="hidden" name="receiver_name" value="${receiver_name}" />
            <input type="hidden" name="receiver_address" value="${receiver_address}" />

            <div class="form-group">
                <label>입금자 명:</label>
                <input type="text" name="payer_name" class="form-control" required />
            </div>
            <div class="form-group">
                <label>입금자 계좌:</label>
                <input type="text" name="payer_account" class="form-control" required />
            </div>
            <div class="form-group">
                <label>결제 금액:</label>
                <div class="info">${totalAmount}</div>
            </div>
            <div class="form-group">
                <label>입금 계좌:</label>
                <div class="info">000-0000-0000</div>
            </div>
            <div class="form-group">
                <label>계좌 명:</label>
                <div class="info">은행 이름</div>
            </div>
            <input type="submit" value="결제 완료" class="btn-submit" />
        </form>
    </div>
</body>
</html>
