<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<h1 style="font-weight:300; margin:40px 0 30px 0; background:white;">"<span class="keyword">키워드</span>" 검색 결과</h1>
	<div id="searchContainer">
		<div class="select">미션(${pDTO.m_totalRecord})</div>
		<div><a href='/ozz/main/searchCom?<c:if test="${pDTO.searchWord != null}">searchWord=${pDTO.searchWord}</c:if>'>커뮤니티(${pDTO.q_totalRecord + pDTO.r_totalRecord + pDTO.b_totalRecord})</a></div>
		<div><a href='/ozz/main/searchMaster?<c:if test="${pDTO.searchWord != null}">searchWord=${pDTO.searchWord}</c:if>'>마스터(${pDTO.u_totalRecord})</a></div>
	</div>
	<div id="searchAll">
		<div id="searchResultMission">
		<c:if test="${empty M_list}">
			<div>${pDTO.searchWord}에 대한 미션 검색 결과가 없습니다.</div>
			<li>검색하신 키워드의 마스터신가요 ? 직접 미션을 작성해보세요. <a href="#">미션 만들기</a></li>
		</c:if>
			<c:forEach var="M_dto" items="${M_list}">
				<div class="searchM">
					<li>${M_dto.mission_title}</li>
					<li><span>${M_dto.mission_tag}</span></li>
					<li style="margin:5px 0 0 5px;">
						<li>${M_dto.userid}</li>
						<li class="masterIcon">
							<span>${M_dto.created_at}</span>
							<span><img src="<%= request.getContextPath()%>/img/Component 3.png"/></span>
							<span>${M_dto.hit}</span>
							<span><img src="<%= request.getContextPath()%>/img/Component 2.png"/></span>
							<span>10</span>
						</li>
					</li>
				</div>
				<hr/>
			</c:forEach>
		
				<div class="searchM">
					<li>고객 난제 정의</li>
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
				<div class="searchM">
					<li>ChatGPT로 영어공부 하기</li>
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
				<div class="searchM">
					<li>디자인 시스템 with XD</li>
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
			<div style="margin-bottom:10px;">추천 미션</div>
			<div id="recommendContent">
				<li>Figma 활용법</li>
				<li><span>스킬</span><span>태그</span></li>
				<li>홍길동</li>
			</div>
			<div id="recommendContent">
				<li>디자인 시스템 이해</li>
				<li><span>스킬</span><span>태그</span></li>
				<li>홍길동</li>
			</div>
			<div id="recommendContent">
				<li>자바스크립트 이해 및 활용</li>
				<li><span>스킬</span><span>태그</span></li>
				<li>홍길동</li>
			</div>
		
		</div>
	</div>

</main>