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
	background: #52B0C5; 
	border-radius: 100px;
	border: none;
	margin: 30px 0;
	color: white;
	display: block;
	float: right;
}
#viewSub:hover {
	color: #52B0C5;
	border: 1px solid #52B0C5;
	background: #E8FBFF;
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
<main style="width:800px; height: 500px;">
	<div class=subPopup>
		<h2>OZZ 구독 내역</h2>
		<div class="subInfo">
			구독 중인 서비스가 없습니다.
		</div>
		<div><button id="viewSub">구독 상품 보기</button></div>
		<div class="subService">
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
		</div>
	</div>


</main>
<script>
	let btn = document.querySelector('button#viewSub');
	const content = document.querySelector('.subService');

	
	btn.addEventListener('click', () => {
        content.classList.add('view');
    })
</script>