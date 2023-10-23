<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
	<h1 style="font-weight:300; margin:40px 0 30px 0; background:white;">"<span class="keyword">${pDTO.searchWord}</span>" 검색 결과</h1>
	<div id="searchContainer">
		<div><a href='/ozz/main/searchMission?<c:if test="${pDTO.searchWord != null}">searchWord=${pDTO.searchWord}</c:if>'>미션(${pDTO.m_totalRecord})</a></div>
		<div class="select">커뮤니티(${pDTO.q_totalRecord + pDTO.r_totalRecord + pDTO.b_totalRecord})</div>
		<div><a href='/ozz/main/searchMaster?<c:if test="${pDTO.searchWord != null}">searchWord=${pDTO.searchWord}</c:if>'>마스터(${pDTO.u_totalRecord})</a></div>
	</div>
	<div id="searchAll">
		<div id="searchResult">
			<c:if test="${empty Q_list and empty R_list and empty B_list}">
				<div class="nosearch">
	        	 	<li><span class="highlight">"${pDTO.searchWord}"</span> 에 대한 커뮤니티 검색 결과가 없습니다.</li>
	         		<li>관련 게시물이 없나요? 게시판에 직접 작성해보세요. <a href="/ozz//Board/Boardwrite" class="goMake"> 게시글 작성하기</a></li>
	      		</div>
      		</c:if>
      		<c:forEach var="Q_dto" items="${Q_list}">
			<div class="searchCom">
					<li><a href='/ozz/Qna/QnaView?no=${Q_dto.qna_no}&nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>[QnA] ${Q_dto.qna_title}</li>
					<li class="searchContent">${Q_dto.qna_content}</li>
					<li></li>
					<li style="margin:5px 0 0 5px;">
						<li>${Q_dto.userid}<span class="masterIcon">
							<span>${Q_dto.created_at}</span>
							<span><img src="<%= request.getContextPath()%>/img/Component 3.png"/></span>
							<span>${Q_dto.hit}</span>
							<span><img src="<%= request.getContextPath()%>/img/Component 2.png"/></span>
							<span>10</span>
						</span></li>
				</div>
				<hr/>
			</c:forEach>
			
			<c:forEach var="R_dto" items="${R_list}">
	            <div class="searchCom">
	               <li>[리뷰] ${R_dto.review_title}</li>
	               <li class="searchContent">${R_dto.review_content}</li>
	               <li></li>
	               <li style="margin:5px 0 0 5px;">
	                  <li>${R_dto.userid}<span class="masterIcon">
	                     <span>${R_dto.created_at}</span>
	                     <span><img src="<%= request.getContextPath()%>/img/Component 3.png"/></span>
	                     <span>${R_dto.hit}</span>
	                     <span><img src="<%= request.getContextPath()%>/img/Component 2.png"/></span>
	                     <span>10</span>
	                  </span></li>
	            </div>
	            <hr/>
	         </c:forEach>
	         
			<c:forEach var="B_dto" items="${B_list}">
	            <div class="searchCom">
	               <li><a href='/ozz/Board/BoardView?no=${B_dto.board_no}&nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>[${B_dto.board_cate}] ${B_dto.board_title}</a></li>
	               <li class="searchContent">${B_dto.board_content}</li>
	               <li><c:forEach items="${fn:split(B_dto.work_cate, ',')}" var="category">
                <span>${category}</span>
            </c:forEach></li>
	               <li style="margin:5px 0 0 5px;">
	                  <li>${B_dto.userid}<span class="masterIcon">
	                     <span>${B_dto.created_at}</span>
	                     <span><img src="<%= request.getContextPath()%>/img/Component 3.png"/></span>
	                     <span>${B_dto.hit}</span>
	                     <span><img src="<%= request.getContextPath()%>/img/Component 2.png"/></span>
	                     <span>10</span>
	                  </span></li>
	            </div>
	            <hr/>
         </c:forEach>
			
				<%-- <div class="searchCom">
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
				<hr/> --%>
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