<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
header, footer {
	display: none;
}
h2::after {
	content:'';
    display:block;
    clear:both;
	border-bottom: solid 2px #DAE2EB;
	margin-top: 20px;
}
#missionGoList {
	display: grid;
	grid-template-columns: 265px 270px 265px;
	height: auto;
}
.missionContent {
	border: 1.5px solid #DAE2EB;
	border-radius: 16px;
	margin: 10px;
	padding: 15px 20px;
}
.missionContent li:nth-child(2) {
	font-size: 0.8em;
	margin-top: 25px;
}
.missionContent li:nth-child(2) span{
	margin-left: 5px;
}
.missionContent li:last-child button {
	text-align: center;
	width:100%; 
	padding: 7px 20px;
	border: 1px solid #52B0C5;
	background: #E8FBFF;
	cursor: pointer;
	border-radius: 100px;
	color: #52B0C5;
}
.missionContent li:last-child button:hover {
	background: #52B0C5;
	color: white;
	
}
</style>


<main style="width:800px; height: 500px;">
	<h2>진행중인 미션</h2>
	<div  id="missionGoList">
		<c:forEach items="${mymissionList}" var="mission_ing">
		<div class="missionContent">
			<li>${mission_ing.mission_title}</li>
			<li>진행률<span>${mission_ing.mission_rate}%</span><span>남은 스텝 수</span><span>00</span></li>
			<li><progress id="progress" value="${mission_ing.mission_rate}" min="0" max="100" style="margin-top: 10px;"></progress></li>
			<li><button>이어서 진행하기</button></li>
		</div>
		</c:forEach>
	</div>
</main>