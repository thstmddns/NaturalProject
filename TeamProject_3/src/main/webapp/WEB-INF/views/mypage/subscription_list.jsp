<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!-- JavaScript 라이브러리를 포함 -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>

<body>
    <p id="subscriptionMessage"></p>
    <a href="/ozz/mypage/paymentForm"><button>구독하기</button></a>

		<c:forEach var="dto" items="${mysublist}" varStatus="status">
		<li>구독옵션:${dto.sub_option}</li>
		<li>구독요금:${dto.pay_amount}</li>
		<li>구독날짜:${dto.started_at}</li>
		</c:forEach>

    <h1>구독 상태 확인</h1>
    <p id="subscriptionMessage"></p>
    <button id="subscribeButton" style="display:none">구독하기</button>

    <script>
        // 사용자 아이디를 파라미터로 받음
        var userId = "<%= request.getParameter("userId") %>";

        // JavaScript로 구독 상태 확인
        var isSubscribed = checkSubscription(userId);

        // 결과에 따라 메시지와 버튼을 업데이트
        var messageElement = document.getElementById("subscriptionMessage");
        var buttonElement = document.getElementById("subscribeButton");

        if (isSubscribed) {
            messageElement.textContent = "구독 중입니다.";
        } else {
            messageElement.textContent = "구독 만료";
            buttonElement.style.display = "block";
            buttonElement.addEventListener("click", function() {
                // 구독 버튼을 클릭했을 때 실행할 동작
                window.location.href = "/subscribe?userId=" + userId;
            });
        }

        // JavaScript 함수로 구독 확인 로직 구현
        function checkSubscription(userId) {
            // 여기에서 사용자의 구독 상태를 확인하는 로직을 구현
            // 예시: 구독 여부에 따라 true 또는 false 반환
            return true; // 가정: 항상 구독 중으로 설정
        }
    </script>
</body>
</html>