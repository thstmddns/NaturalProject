<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div id="landing">
	    <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 20px; display: inline-flex;">
	        <div style="margin-top: 10px;"><span style="color: #222A2F; font-size: 30px; word-wrap: break-word;">직접 미션을 만들어<br/>다른 사용자의 랜선사수가 되어주세요!</span></div>
	            <button id="landBtn">미션 만들기</button>
	    </div>
	    <div id="landingImage" style="display:flex; margin-left:70px; width: 470px; height: 320px;">
	        <img src="<%= request.getContextPath()%>/img/Group 7169.png"/>
	    </div>
	</div>
	
	<div style="margin-top:90px;">
		<span style="font-size:1.6em;">진행 중인 미션</span>
		<span><a style="font-size:0.8em; color: #869AAF; float:right;">더보기 ></a></span>
	</div>
	<div id="mIng">
		<div id="ingList">
			<div class="ingM">
				<li>PM을 위한 데이터 리터러시</li>
				<li>
					<span>진행률 20%</span>
					<span>남은 스텝 수 00개</span>
				</li>
				<li><progress id="progress" value="20" min="0" max="100"></li>
				<li><button>이어서 진행하기</button></li>
			</div>
		</div>
		<div id="ingList">
			<div class="ingM">
				<li>다양한 사례로 익히는 SQL 데이터 분석</li>
				<li>
					<span>진행률 20%</span>
					<span>남은 스텝 수 00개</span>
				</li>
				<li><progress id="progress" value="20" min="0" max="100"></li>
				<li><button>이어서 진행하기</button></li>
			</div>
		</div>
		<div id="ingList">
			<div class="ingM">
				<li>클라우드 서비스 AWS</li>
				<li>
					<span>진행률 20%</span>
					<span>남은 스텝 수 00개</span>
				</li>
				<li><progress id="progress" value="20" min="0" max="100"></li>
				<li><button>이어서 진행하기</button></li>
			</div>
		</div>
	</div>
	
	<div style="font-size:1.6em; margin-top: 90px;">맞춤 추천 미션</div>
	<div id="recommendM">
		<div id="recommendMList">
			<div class="rMInfo">
				<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (1).png"/></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (3).png"/></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img src="<%= request.getContextPath()%>/img/Frame 2526.png"/></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (2).png"/></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (1).png"/></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
			<div class="rMInfo">
				<li><img src="<%= request.getContextPath()%>/img/Frame 2526.png"/></li>
				<li>ChatGPT로 영어공부 하기</li>
				<li>ChatGPT를 나만의 영어 멘토로: 영어 공부 활용법(단어장 템플릿 제공) 일잘러의 업무스킬</li>
				<li><span>ChatGPT</span><span>영어</span></li>
				<li>장호찬</li>
			</div>
		</div>
		
	</div>
	
	
		<div id="popM">
			<li style="font-size:1.6em">주간 인기 미션 Top 10</li>
			<li style="color: #869AAF; margin-top:5px; font-size:0.9em">지금 가장 많이 도전하고 있는 미션</li>
		</div>
		<div id="popMList">
			<div class="popMContent">
				<div>01</div>
				<div><img src="<%= request.getContextPath()%>/img/image 70.jpg"/></div>
				<div class="popMInfo">
				<li>고객 경험 디자인</li>
				<li><span>커뮤니케이션</span><span>리액트</span></li>
				<li>김수인</li>
				</div>
			</div>
			<div class="popMContent">
				<div>02</div>
				<div><img src="<%= request.getContextPath()%>/img/image 78.jpg"/></div>
				<div class="popMInfo">
				<li>고객 경험 디자인</li>
				<li><span>UX</span><span>UI</span><span>피드마</span></li>
				<li>최현경</li>
				</div>
			</div>
			<div class="popMContent">
				<div>03</div>
				<div><img src="<%= request.getContextPath()%>/img/image 79.jpg"/></div>
				<div class="popMInfo">
				<li>고객 경험 디자인</li>
				<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
				<li>김동현</li>
				</div>
			</div>
			<div class="popMContent">
				<div>04</div>
				<div><img src="<%= request.getContextPath()%>/img/image 80.jpg"/></div>
				<div class="popMInfo">
				<li>고객 경험 디자인</li>
				<li><span>태그</span></li>
				<li>황윤재</li>
				</div>
			</div>
		</div>
	</div>
	
</main>