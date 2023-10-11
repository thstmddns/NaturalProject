<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div id="landingM">
	    <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 20px; display: inline-flex;">
	        <div style="margin-top: 60px;"><span style="color: #222A2F; font-size: 30px; word-wrap: break-word;">일하는 사람들의 모든 이야기를 나누어보세요.</span></div>
	            <button id="landBtn" onclick="location.href='<%=request.getContextPath() %>/Board/Boardwrite'">커뮤니티에 글쓰기</button>
	    </div>
	    <div id="landingImage" style="display:flex; margin-left:60px; width: 580px; height: 280px;">
	        <img src="<%= request.getContextPath()%>/img/6.png"/>
	    </div>
	</div>
	
	<div id="mainComPop">
			<li style="font-size:1.6em">주간 인기 커뮤니티 Top10</li>
			<li style="color: #869AAF; margin-top:5px; font-size:0.9em">지금 가장 핫한 반응의 커뮤니티</li>
	</div>
	<div id="mainComPopL">
			<div class="mainComPopInfo">
				<div>01</div>
				<div class="mainComInfo">
				<li>[Q&A] 리액트 파일 업로드 관련</li>
				<li>안녕하세요 픽스한지 6개월 정도 된 프론트앤드 개발자입니다. 회사에서 기존 프로젝트에 사용 중이던 가변적 회로가 9월에 만료되기 때문에 업데이트 검토 관련 문제가 있었습니다. 버전을 올리...</li>
				<li><span>UX</span><span>UI</span><span>피그마</span></li>
				<li style="margin:5px 0 0 5px;">
					<li>김지웅</li>
					<li class="masterIcon">
						<span>2023-09-22</span>
						<span><img src="<%= request.getContextPath()%>/img/Component 3.png"/></span>
						<span>10</span>
						<span><img src="<%= request.getContextPath()%>/img/Component 2.png"/></span>
						<span>10</span>
					</li>
				</li>
				</div>
			</div>
			<div class="mainComPopInfo">
				<div>02</div>
				<div class="mainComInfo">
				<li>[태스크] 페르소나 분석</li>
				<li>안녕하세요 픽스한지 6개월 정도 된 프론트앤드 개발자입니다. 회사에서 기존 프로젝트에 사용 중이던 가변적 회로가 9월에 만료되기 때문에 업데이트 검토 관련 문제가 있었습니다. 버전을 올리...</li>
				<li><span>UX</span><span>UI</span><span>피그마</span></li>
				<li style="margin:5px 0 0 5px;">
					<li>서인국</li>
					<li class="masterIcon">
						<span>2023-09-22</span>
						<span><img src="<%= request.getContextPath()%>/img/Component 3.png"/></span>
						<span>10</span>
						<span><img src="<%= request.getContextPath()%>/img/Component 2.png"/></span>
						<span>10</span>
					</li>
				</li>
				</div>
			</div>
			<div class="mainComPopInfo">
				<div>03</div>
				<div class="mainComInfo">
				<li>[Q&A] 백엔드 코드를 아름답게 분리하고 싶어요</li>
				<li>안녕하세요 픽스한지 6개월 정도 된 프론트앤드 개발자입니다. 회사에서 기존 프로젝트에 사용 중이던 가변적 회로가 9월에 만료되기 때문에 업데이트 검토 관련 문제가 있었습니다. 버전을 올리...</li>
				<li><span>UX</span><span>UI</span><span>피그마</span></li>
				<li style="margin:5px 0 0 5px;">
					<li>차은우</li>
					<li class="masterIcon">
						<span>2023-09-22</span>
						<span><img src="<%= request.getContextPath()%>/img/Component 3.png"/></span>
						<span>10</span>
						<span><img src="<%= request.getContextPath()%>/img/Component 2.png"/></span>
						<span>10</span>
					</li>
				</li>
				</div>
			</div>
			<div class="mainComPopInfo">
				<div>04</div>
				<div class="mainComInfo">
				<li>[게시글] 개발자를 준비하는 친구에게 10가지 격려의 메시지</li>
				<li>안녕하세요 픽스한지 6개월 정도 된 프론트앤드 개발자입니다. 회사에서 기존 프로젝트에 사용 중이던 가변적 회로가 9월에 만료되기 때문에 업데이트 검토 관련 문제가 있었습니다. 버전을 올리...</li>
				<li><span>UX</span><span>UI</span><span>피그마</span></li>
				<li style="margin:5px 0 0 5px;">
					<li>조인성</li>
					<li class="masterIcon">
						<span>2023-09-22</span>
						<span><img src="<%= request.getContextPath()%>/img/Component 3.png"/></span>
						<span>10</span>
						<span><img src="<%= request.getContextPath()%>/img/Component 2.png"/></span>
						<span>10</span>
					</li>
				</li>
				</div>
			</div>
		</div>
	</div>
	
	
</main>