<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
	<h1 style="font-weight:300; margin:40px 0 30px 0; background:white;">"<span class="keyword">${pDTO.searchWord}</span>" 검색 결과</h1>
	<div id="searchContainer">
		<div class="select">미션(${pDTO.m_totalRecord})</div>
		<div><a href='/ozz/main/searchCom?<c:if test="${pDTO.searchWord != null}">searchWord=${pDTO.searchWord}</c:if>'>커뮤니티(${pDTO.q_totalRecord + pDTO.r_totalRecord + pDTO.b_totalRecord})</a></div>
		<div><a href='/ozz/main/searchMaster?<c:if test="${pDTO.searchWord != null}">searchWord=${pDTO.searchWord}</c:if>'>마스터(${pDTO.u_totalRecord})</a></div>
	</div>
	<div id="searchAll">
		<div id="searchResultMission">
			<c:if test="${empty M_list}">
				<div class="nosearch">
	         		<li><span class="highlight">"${pDTO.searchWord}"</span> 에 대한 미션 검색 결과가 없습니다.</li>
	         		<li>검색하신 키워드의 마스터신가요 ?  직접 미션을 작성해보세요. <a href="/ozz/Mission/Missionwrite" class="goMake"> 직접 미션 만들기</a></li>
	      		</div>
      		</c:if>
      		 <c:forEach var="M_dto" items="${M_list}">
				<div class="searchM">
	         		<li><a href='/ozz/Mission/MissionView?no=${M_dto.mission_no}&nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>${M_dto.mission_title}</a></li>
					<li><c:forEach items="${fn:split(M_dto.mission_tag, ',')}" var="category">
                <span>${category}</span></c:forEach></li>
						<li style="margin-top: 30px;">${M_dto.userid}<span class="masterIcon">
							<span>${M_dto.created_at}</span>
							<span><img src="<%= request.getContextPath()%>/img/Component 3.png"/></span>
							<span>${M_dto.hit}</span>
							<span><img src="<%= request.getContextPath()%>/img/Component 2.png"/></span>
							<span>10</span>
						</span></li>
	      		</div>
	      		<hr/>
      		</c:forEach>
			<%-- <div class="searchM">
					<li>고객 경험 리디자인</li>
					<li><span>UX</span><span>UI</span><span>피그마</span></li>
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
				</div> --%>
				<%-- <hr/>
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
				<hr/> --%>
		</div>
		
		<div id="recommend">
			<div style="margin-bottom:10px;">추천 미션</div>
			<div id="recommendContent">
				<li>${search["Title"][0].replaceAll("[\\[\\]']", '')}</li>
				<c:forEach items="${fn:split(search['Tag'][0], ',')}" var="tag">
			    	<span class="spanTag">${tag.replaceAll("[\\[\\]']", '')}</span>
				</c:forEach>
				<li>${search["Author"][0][0].replaceAll("[\\[\\]']", '') }</li>
			</div>
			<div id="recommendContent">
				<li>${search["Title"][1].replaceAll("[\\[\\]']", '')}</li>
				<c:forEach items="${fn:split(search['Tag'][1], ',')}" var="tag">
			    	<span class="spanTag">${tag.replaceAll("[\\[\\]']", '')}</span>
				</c:forEach>
				<li>${search["Author"][1][0].replaceAll("[\\[\\]']", '') }</li>
			</div>
			<div id="recommendContent">
				<li>${search["Title"][2].replaceAll("[\\[\\]']", '')}</li>
				<c:forEach items="${fn:split(search['Tag'][2], ',')}" var="tag">
			    	<span class="spanTag">${tag.replaceAll("[\\[\\]']", '')}</span>
				</c:forEach>
				<li>${search["Author"][2][0].replaceAll("[\\[\\]']", '') }</li>
			</div>
		
		</div>
	</div>

</main>