<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
header, footer {
	display: none;
}
.subInfo {
	width: 87%;
	text-align:center;
	padding: 50px;
	height: auto;
	background: #F1F4F8;
	box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.10);
}
.subService {
	display: none;
}
h2::after {
	content:'';
    display:block;
    clear:both;
	border-bottom: solid 2px #DAE2EB;
	margin-top: 15px;
}
.view {
	display: block;
  	background: white;
    height:auto;
    width: 100%;
    margin-top: 90px;
    display: grid;
    grid-template-columns: 405px 405px;
}
#viewSub {
	width: auto;
	height: auto;
	padding: 10px 20px; 
	border-radius: 100px;
	margin: 30px 0;
	display: block;
	float: right;
	border: 1px solid #52B0C5;
	background:  #E8FBFF;
	color: #52B0C5;
}
#viewSub:hover {
	color: white;
	background: #52B0C5;
}
.subServiceDetail {
	border-radius: 5px;
	border: none;
	background: #F1F4F8;
	margin: 0 15px 20px 0;
	box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.10);
	padding: 10px 0 10px 20px;
}
.subServiceDetail  li {
	margin: 10px 0;
}
.subServiceDetail  li:first-child {
	color: #F93D18;
}
.subServiceDetail  li:last-child {
	font-size: 0.9em;
}
.subServiceDetail li:nth-child(2) span:first-child {
	text-decoration: line-through;
}
.subServiceDetail li:first-child span{
	font-size: 0.9em;
}
</style>
<main style="width:600px; height: 500px;">
	<div class=subPopup>
	
		<%
	    // 여기에서 데이터베이스로부터 사용자의 만료 날짜를 가져와야 합니다.
	    // 사용자의 만료 날짜를 endDate 변수에 할당하세요.
	    String endDate = "2023-10-11"; // 예시로 임시로 지정한 만료 날짜
	
	    // 현재 날짜를 가져오는 Java 코드
	    java.util.Date currentDate = new java.util.Date();
	
	    // 만료 날짜를 파싱하여 Date 객체로 변환
	    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date expirationDate = dateFormat.parse(endDate);
	
	    // 구독 가능 여부를 판단
	    boolean isSubscriptionExpired = currentDate.after(expirationDate);
	%>
		<h2>OZZ 구독 내역</h2>
		<div class="subInfo">
			<ul>
			    <c:if test="<%= !isSubscriptionExpired %>">
			        <li>구독 중입니다.</li>
			    </c:if>
			    <c:if test="<%= isSubscriptionExpired %>">
			        <li>구독이 만료되었습니다. 결제 가능합니다.</li>
			    </c:if>
			     <p id="subscriptionMessage"></p>
			</ul> 
		</div>
		<div>
			<h3>지난 결제 내역</h3>
			<c:forEach var="dto" items="${mysublist}" varStatus="status">
			<li>구독옵션:${dto.sub_option}</li>
			<li>구독요금:${dto.pay_amount}</li>
			<li>구독날짜:${dto.started_at}</li>
			</c:forEach>
		</div>
		<div><a href="/ozz/mypage/paymentForm"><button id="viewSub">구독하기</button></a></div>
		<!-- <div class="subService">
			<div class="subServiceDetail">
				<li>1개월권</li>
				<li>10,000원</li>
			</div>
			<div class="subServiceDetail">
				<li>3개월권 <span>(5% 할인)</span></li>
				<li><span>30,000원  </span>→<span>  28,500원</span></li>
			</div>
			<div class="subServiceDetail">
				<li>6개월권 <span>(8% 할인)</span></li>
				<li><span>60,000원  </span>→<span>  55,200원</span></li>
			</div>
			<div class="subServiceDetail">
				<li>12개월권 <span>(10% 할인)</span></li>
				<li><span>120,000원  </span>→<span>  108,000원</span></li>
			</div>
		</div> -->
	</div>


</main>
<!-- <script>
	let btn = document.querySelector('button#viewSub');
	const content = document.querySelector('.subService');

	
	btn.addEventListener('click', () => {
        content.classList.add('view');
    })
</script> -->

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