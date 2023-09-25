<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<h1 style="font-weight:300; margin:40px 0 30px 0; background:white;">"<span class="keyword">키워드</span>" 검색 결과</h1>
	<div id="searchContainer">
		<div>미션(00)</div>
		<div>커뮤니티(00)</div>
		<div>마스터(00)</div>
	</div>
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
		<div></div>
		<div></div>
		<div></div>
	</div>

</main>