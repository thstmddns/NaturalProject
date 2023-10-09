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
<%
    // 여기에서 데이터베이스로부터 사용자의 만료 날짜를 가져와야 합니다.
    // 사용자의 만료 날짜를 endDate 변수에 할당하세요.
    String endDate = "2023-10-4"; // 예시로 임시로 지정한 만료 날짜

    // 현재 날짜를 가져오는 Java 코드
    java.util.Date currentDate = new java.util.Date();

    // 만료 날짜를 파싱하여 Date 객체로 변환
    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
    java.util.Date expirationDate = dateFormat.parse(endDate);

    // 구독 가능 여부를 판단
    boolean isSubscriptionExpired = currentDate.after(expirationDate);
%>

<ul>
	<c:forEach var="dto" items="${mysublist}" varStatus="status">
			<li>구독옵션:${dto.sub_option}</li>
			<li>구독요금:${dto.pay_amount}</li>
	</c:forEach>
    <c:if test="<%= !isSubscriptionExpired %>">
        <li>구독 중입니다.</li>
    </c:if>
    <c:if test="<%= isSubscriptionExpired %>">
        <li>구독이 만료되었습니다. 결제 가능합니다.</li>
        <a href="/ozz/mypage/paymentForm"><button>구독하기</button></a>
        
    </c:if>
</ul>


</body>
</html>