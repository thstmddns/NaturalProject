<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div id="landing">
	    <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 20px; display: inline-flex;">
	        <div style="margin-top: 10px;"><span style="color: #222A2F; font-size: 30px; word-wrap: break-word;">어떻게 일 할까 고민될 땐,<br/></span><span style="color: #222A2F; font-size: 40px; word-wrap: break-word">워크레시피 Ozz!</span></div>
	            <c:if test="${empty logId}">
	            <button id="landBtn" onclick="location.href='<%=request.getContextPath() %>/register/registerJoin'">회원가입하기</button>
	            </c:if>
	    </div>
	    <div id="landingImage" style="display:flex; margin-left:90px;">
	        <div style="position:absolute"><img src="<%= request.getContextPath() %>/img/Ellipse.png" style="width: 115.45px; height: 115.45px;"/>
	        <img src="<%= request.getContextPath() %>/img/Chart.png" style="width: 120.45px; height: 90.98px; margin-left: 40px;"/></div>
	        <div style="position:relative;"><span><img src="<%= request.getContextPath() %>/img/Woman.png" style="width: 130px; height: 195px;"/>
	        <img src="<%= request.getContextPath() %>/img/Chart 1.png"  style="width: 90.45px; height: 60.98px;"/>
	        <img src="<%= request.getContextPath() %>/img/Man.png" style="width: 140px; height: 185px;"/></span></div>
	    </div>
	</div>
	
<!-- 	<div class="search"><input type="text" id="searchLand" placeholder="지금 어떤 업무 도움이 필요하신가요?"/>
	<button id="landBtn2">검색하기</button></div> -->
    <div style="margin-top:10px;">
	    <form action="/ozz/main/searchMission" style="display: flex;" class="input-container">
	    	<input type="text" name="searchWord" id="searchLand" placeholder="지금 어떤 업무 도움이 필요하신가요?">
			<input type="submit" id="landBtn2" value="검색">
		</form>
	</div>
	
	<c:if test="${not empty logId}">
	<div id="landInfo">
		 <div>
		 	<li style="font-size:2em; margin-bottom: 10px;">0</li>
		 	<li>미션 수</li>
		 </div>
		 <div>
		 	<li style="font-size:2em; margin-bottom: 10px;">0</li>
		 	<li>수행된 미션 수</li>
		 </div>
		 <div>
		 	<li style="font-size:2em; margin-bottom: 10px;">0</li>
		 	<li>회원 수</li>
		 </div>
	</div>
	</c:if>
	
	<div id="landImage2">
		<div>
		<img src="<%= request.getContextPath()%>/img/5.png" style="width:400px; height: 300px;"/>
		</div>
		<div style="margin-top:30px;">
			<div>
			<li style="font-size:1.3em;"><i class="fa-solid fa-check" style="color: #52B0C5;"></i>&nbsp&nbsp당신이 일할 때 꼭 필요한 워크레시피!</li>
			<li style="color:#5a5a5a; margin: 20px 0 0 25px">게임하는 것처럼 일하고, 일하면서 배울 수 있습니다.
				</br>업무에 대해 단계별로 알려드리고 미션을 수행할 수 있도록 도와드립니다. 
				</br>실무 프로세스 미션을 수행하며 업무 성장을 이루어 보세요!</li>
			</div>
			<div style="margin-top: 30px;">
			<li style="font-size:1.3em;"><i class="fa-solid fa-check" style="color: #52B0C5;"></i>&nbsp&nbsp누군가는 당신과 같은 일을 하고 있지 않을까요?</li>
			<li style="color:#5a5a5a; margin: 20px 0 0 25px">당신이 하고 있는 일의 가장 비슷한 레퍼런스를 확인하세요.
				</br>모르는 것은 랜선사수에게 바로 물어보세요.
				</br>실제 업무처럼 미션을 수행하고 랜선 사수와 소통하고 피드백을 받을 수 있습니다.</li>
			</div>
		</div>
	</div>
	
	<c:if test="${not empty logId}">
	<div style="margin-top:90px;">
		<span style="font-size:1.6em;">진행 중인 미션</span>
		<span><a href="/ozz/mypage/mypage_main" style="font-size:0.8em; color: #869AAF; float:right;">더보기 ></a></span>
	</div>
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
			<li style="font-size:1.6em">인기 미션 Top 10</li>
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
				<li><span>UX</span><span>UI</span><span>피그마</span></li>
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
	</c:if>
	
	<div id="popMas">
		<div style="font-size:1.6em; margin: 90px 0 35px 0">이 달의 마스터</div>
			<div id="popMa">
				<div id="popMaInfo">
					<span><img src="<%= request.getContextPath()%>/img/Frame 2.png"/></span>
					<span>손승운</span>
				</div>
				<div id="popMaContent">
					<div class="popMaContents">
						<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (1).png"/></li>
						<li>클라우드 서비스 AWS</li>
						<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
					</div>
					<div class="popMaContents">
						<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (2).png"/></li>
						<li>따라하며 배우는 리액트 A-Z</li>
						<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
					</div>
					<div class="popMaContents">
						<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (3).png"/></li>
						<li>ProtoPie Master Courses</li>
						<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
					</div>
				</div>
			</div>
				<div id="popMa">
					<div id="popMaInfo">
						<span><img src="<%= request.getContextPath()%>/img/Frame 2.png"/></span>
						<span>임용진</span>
					</div>
					<div id="popMaContent">
						<div class="popMaContents">
							<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (3).png"/></li>
							<li>클라우드 서비스 AWS</li>
							<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
						</div>
						<div class="popMaContents">
							<li><img src="<%= request.getContextPath()%>/img/Frame 2526.png"/></li>
							<li>따라하며 배우는 리액트 A-Z</li>
							<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
						</div>
						<div class="popMaContents">
							<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (3).png"/></li>
							<li>ProtoPie Master Courses</li>
							<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
						</div>
					</div>
				</div>
	</div>
</main>