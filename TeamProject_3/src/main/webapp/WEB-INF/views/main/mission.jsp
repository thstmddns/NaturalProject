<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>
	<div id="landing">
		<div
			style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 20px; display: inline-flex;">
			<div style="margin-top: 50px;">
				<span
					style="color: #222A2F; font-size: 30px; word-wrap: break-word;">직접
					미션을 만들어<br />다른 사용자의 랜선사수가 되어주세요!
				</span>
			</div>
			<button id="landBtn" onclick="location.href='<%=request.getContextPath() %>/main/Missionwrite'">미션 만들기</button>
		</div>
		<div id="landingImage"
			style="display: flex; margin-left: 70px; width: 470px; height: 320px;">
			<img src="<%=request.getContextPath()%>/img/Group 7169.png" />
		</div>
	</div>

	<div style="margin-top: 90px;">
		<span style="font-size: 1.6em;">진행 중인 미션</span> <span><a
			style="font-size: 0.8em; color: #869AAF; float: right;">더보기 ></a></span>
	</div>
	<c:if test="${empty logId}">
		로그인 후 사용하세요 <a href="/ozz/register/login">로그인</a>
	</c:if>
		<div id="mIng">
			<c:forEach var="P_dto" items="${Perfomerslist}">
				<c:if test="${logId == P_dto.userid}">
					<div id="ingList">
						<div class="ingM">
							<li>${P_dto.mission_title}</li>
							<li><span>진행률 ${P_dto.mission_rate}%</span> <span>남은 스텝 수 00개</span></li>
							<li><progress id="progress" value="${P_dto.mission_rate}" min="0" max="100"></li>
							<li><button>이어서 진행하기</button></li>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	
	
	<div style="font-size: 1.6em; margin-top: 90px;">맞춤 추천 미션</div>
	<div id="recommendM">
		<div id="recommendMList">
			<div class="rMInfo">
				<li><img
					src="<%=request.getContextPath()%>/img/Frame 2526 (1).png" /></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img
					src="<%=request.getContextPath()%>/img/Frame 2526 (3).png" /></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img src="<%=request.getContextPath()%>/img/Frame 2526.png" /></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img
					src="<%=request.getContextPath()%>/img/Frame 2526 (2).png" /></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img
					src="<%=request.getContextPath()%>/img/Frame 2526 (1).png" /></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img src="<%=request.getContextPath()%>/img/Frame 2526.png" /></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
		</div>

	</div>


	<div id="popM">
		<li style="font-size: 1.6em">인기 미션 Top 10</li>
		<li style="color: #869AAF; margin-top: 5px; font-size: 0.9em">지금
			가장 많이 도전하고 있는 미션</li>
	</div>
	<div id="popMList">
		<c:forEach var="M_dto" items="${MissionToplist}" varStatus="loop">
			<div class="popMContent">
				<c:choose>
					<c:when test="${loop.index + 1 < 10}">
						<span style="margin-bottom:20px;">
							0<c:out value="${loop.index + 1}" />
						</span>
						<!-- 01 자리에 반복 인덱스를 표시 -->
					</c:when>
					<c:otherwise>
						<div>
							<c:out value="${loop.index + 1}" />
						</div>
					</c:otherwise>
				</c:choose>
				<!-- 01 자리에 반복 인덱스를 표시 -->
				<div>
					<img src="<%=request.getContextPath()%>/img/image 70.jpg" />
				</div>
				<div class="popMInfo">
					<li><a href="/ozz/Mission/MissionView?no=${M_dto.mission_no}">${M_dto.mission_title}</a></li>
					<li><span class="spanTag">${M_dto.mission_tag}</span></li>
					<li>${M_dto.userid}</li>
				</div>
			</div>
		</c:forEach>
	</div>
	</div>

</main>