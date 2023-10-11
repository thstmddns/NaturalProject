<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<h1 style="font-weight:300; margin:40px 0 30px 0; background:white;">"<span class="keyword">키워드</span>" 검색 결과</h1>
	<div id="searchContainer">
		<div>미션(00)</div>
		<div class="select">커뮤니티(00)</div>
		<div>마스터(00)</div>
	</div>
	<div id="searchAll">
		<div id="searchResult">
			<div class="searchCom">
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
				<hr/>
				<div class="searchCom">
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
				<hr/>
				<div class="searchCom">
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
				<hr/>
				<div class="searchCom">
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
				<hr/>
		</div>
		
		<div id="recommend">
			<div style="margin-bottom:10px;">추천 커뮤니티</div>
			<div id="recommendContent">
				<li>[태스크] 페르소나 분석</li>
				<li><span>스킬</span><span>태그</span></li>
				<li>홍길동</li>
			</div>
			<div id="recommendContent">
				<li>[게시글] Chat GPT 활용법</li>
				<li><span>스킬</span><span>태그</span></li>
				<li>홍길동</li>
			</div>
			<div id="recommendContent">
				<li>[질문] Figma 어떻게 쓰나요?</li>
				<li><span>스킬</span><span>태그</span></li>
				<li>홍길동</li>
			</div>
		
		</div>
	</div>

</main>