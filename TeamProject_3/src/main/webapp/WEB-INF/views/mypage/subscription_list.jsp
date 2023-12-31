<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
header, footer {
   display: none;
}
.subInfo {
   width: 83%;
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
   margin-bottom: 80px;
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
#expired {
   display:grid;
   grid-template-columns: 300px 150px;
}
.expiredSub {
   width: 170px;
   padding: 50px;
   height: auto;
   background: #F1F4F8;
   box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.10);
   margin-right: 20px;
}
</style>
<main style="width:600px; height: 500px;">
   <div class=subPopup>

      <h2>나의 구독 상태</h2>
		<div class="subInfo">
		    <ul>
		        <c:choose>
		            <c:when test="${mysubstatus == '1'}">
		                <li>구독 중입니다.</li>
		            </c:when>
		            <c:otherwise>
		                <li>구독이 만료되었습니다. 결제 가능합니다.</li>
		                <div><a href="/ozz/mypage/paymentForm"><button id="viewSub">구독하기</button></a></div>
		            </c:otherwise>
		        </c:choose>
		        <p id="subscriptionMessage"></p>
		    </ul> 
		</div>


      <div style="margin-top:60px;">
         <h3 style="color:#F93D18;">지난 결제 내역</h3>
         <div id="expired">
            <c:forEach var="dto" items="${mysublist}" varStatus="status">
	            <div class="expiredSub">
	               <li>구독옵션 : ${dto.sub_option}</li>
	               <li>구독요금 : ${dto.pay_amount}</li>
	               <li>구독날짜 : ${dto.started_at}</li>
	            </div>
            </c:forEach>
         </div>
      </div>

   </div>


</main>
<!-- <script>
   let btn = document.querySelector('button#viewSub');
   const content = document.querySelector('.subService');

   
   btn.addEventListener('click', () => {
        content.classList.add('view');
    })
</script> -->
