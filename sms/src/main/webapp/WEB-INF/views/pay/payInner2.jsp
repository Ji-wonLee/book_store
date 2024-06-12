<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>금액 결제</title>
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
</head>
<body>
    <h1>금액 결제</h1>
    <a href="/sms/customermain">메인화면</a>
    <form action="/sms/payment/completePayment" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="user_id" value="${user_id}" />
        <input type="hidden" name="receiver_name" value="${receiver_name}" />
        <input type="hidden" name="receiver_address" value="${receiver_address}" />
        <label>입금자 명:</label>
        <input type="text" name="payer_name" required /><br />
        <label>입금자 계좌:</label>
        <input type="text" name="payer_account" required /><br />
        <label>결제 금액:</label>
        <span>${totalAmount}</span><br />
        <label>입금 계좌:</label>
        <span>000-0000-0000</span><br />
        <label>계좌 명:</label>
        <span>은행 이름</span><br />
        <input type="submit" value="결제 완료" />
    </form>
</body>
</html>
