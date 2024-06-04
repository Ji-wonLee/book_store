<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>결제진행</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<style>
.header-container {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
	padding: 0 20px;
}

.header-container h3 {
	margin: 0;
}
</style>
</head>
<body>
	<div class="max-w-4xl mx-auto">
		<div class="header-container">
			<h3 style="margin-left: 60%;">{$user_name}님 환영합니다.</h3>
			<button type="button" >상품 조회</button	>
		</div>


		<div class="max-w-4xl mx-auto">
			<div class="flex items-center space-x-2 border-b p-4">
				<input type="checkbox"></input> <label for="selectAll"
					class="text-sm font-medium leading-none">전체선택</label>
			</div>

			<%-- JSP 스크립틀릿을 사용하여 서버 측에서 데이터 반복 생성 --%>
			<%
			for (int i = 1; i <= 3; i++) {
			%>
			<div class="border-b p-4">
				<div class="flex items-center justify-between">
					<div class="flex items-center space-x-2">
						<input type="checkbox"></input> <img
							src="./grapefruit-slice-332-332.jpg" alt="Product Image"
							class="w-12 h-12 rounded" width="48" height="48"
							style="aspect-ratio: 48/48; object-fit: cover;" /> <label
							for="item<%=i%>" class="text-sm font-medium leading-none">클래식
							티셔츠</label>
					</div>
					<div class="space-x-2">
						<span class="text-sm">정상가: <%=20000 * i%> 원
						</span> <span class="text-sm font-medium">판매가: <%=15400 * i%> 원 
					</div>
					<div class="flex items-center space-x-2">
						<input type="number" min="1" value=1
							style="border: 1px solid gray; width: 50px;"></input> <span
							class="text-sm font-medium"><%=15400 * i%> 원</span>
					</div>
					<div class="flex items-center space-x-2">
						<button
							class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 px-4 py-2">삭제</button>
					</div>
				</div>
			</div>
			<%
			}
			%>

			<div class="flex justify-between bg-[#f3f6f9] p-4">
				<div class="space-y-1">
					<div class="flex justify-between">
						<span class="text-sm">총 상품 가격</span> <span
							class="text-sm font-medium">239,400 원</span>
					</div>
					<div class="flex justify-between">
						<span class="text-sm">배송비</span> <span class="text-sm font-medium">0
							원</span>
					</div>
					<div class="flex justify-between">
						<span class="text-sm">총 주문 상품수</span> <span
							class="text-sm font-medium">3 종 8건</span>
					</div>
				</div>
				<div class="space-y-1">
					<div class="flex justify-between">
						<span class="text-sm">총 결제 예정 금액</span> <span
							class="text-sm font-medium">239,400 원</span>
					</div>
				</div>
			</div>
			<button type="button" class="btn btn-outline-dark"
				style="margin-top: 5%; float: right;">결제하기</button>
		</div>
	</div>
</body>
</html>