<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.Qna_list, .page>ul {
	overflow: auto;
}

.Qna_list>li {
	float: left;
	height: 40px; line-height =40px;
	border-bottom: 1px solid #ddd;
	width: 10%;
}

.Qna_list>li:nth-child(4n+2) {
	width: 70%;
	/*말 줄임 표시*/
	white-space: nowrap; /*줄바꾸지 않기*/
	overflow: hidden; /*넘친 값 숨기기*/
	text-overflow: ellipsis; /* ...표시하기*/
}

.page>ul {
	overflow: auto;
}

.page li {
	float: left;
	width: 40px;
	height: 40px;
	text-align: center;
}

.search {
	text-align: center;
}
</style>
<main>
	<h1>QnA 목록</h1>
	<div>
		<a href="/ozz/Qna/Qnawrite?m_no=0">글쓰기</a>
	</div>
	<div>총 레코드 수 : ${pDTO.totalRecord}개</div>
	<ul class="Qna_list">

		<li>no</li>
		<li>제목</li>
		<li>글쓴이</li>
		<li>등록일</li>

		<!--  변수 데이터(List) -->
		<c:forEach var="dto" items="${list}">
			<li>${dto.qna_no}</li>
			<li><a
				href='/ozz/Qna/QnaView?no=${dto.qna_no}&nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>${dto.qna_title}</a></li>
<%-- 			<c:choose>
				<c:when test="${dto.file_name_base64 != 'None'}">
					<img src="data:image/jpeg;base64,${dto.file_name_base64}"
						alt="Product Image" 
						style="width: 200px; height: 200px;"/>
				</c:when>
				<c:otherwise>
					<img src="default-image.jpg" alt="Default Image" style="width: 200px; height: 200px;"/>
				</c:otherwise>
			</c:choose> --%>

			<li>${dto.userid}</li>
			<li>${dto.created_at}</li>
		</c:forEach>
	</ul>
	<div class="page">
		<ul>
			<!-- 이전 페이지 -->
			<c:if test="${pDTO.nowPage==1}">
				<li>prev</li>
			</c:if>

			<c:if test="${pDTO.nowPage>1}">
				<li><a
					href='/ozz/Qna/Qnalist?nowPage=${pDTO.nowPage-1}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>prev</a></li>
			</c:if>

			<!-- 페이지 번호 -->
			<!--  -->
			<c:forEach var="p" begin="${pDTO.startPageNum}"
				end="${pDTO.startPageNum+pDTO.onePageNumCount-1}" step="1">
				<c:if test="${p<=pDTO.totalPage}">
					<!-- 값이 있는 페이지까지만 출력 -->
					<c:if test="${p==pDTO.nowPage}">
						<li style="background: yellow"><a
							href='/ozz/Qna/Qnalist?nowPage=${p}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>${p}</a></li>
					</c:if>
					<c:if test="${p!=pDTO.nowPage}">
						<li><a
							href='/ozz/Qna/Qnalist?nowPage=${p}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>${p}</a></li>
					</c:if>
				</c:if>
			</c:forEach>

			<!-- 다음 페이지 -->
			<c:if test="${pDTO.nowPage>=pDTO.totalPage}">
				<li>next</li>
			</c:if>
			<c:if test="${pDTO.nowPage<pDTO.totalPage}">
				<li><a
					href='/ozz/Qna/Qnalist?nowPage=${pDTO.nowPage+1}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>next</a></li>
			</c:if>
		</ul>
	</div>
	<div class="search">
		<form action="/ozz/Qna/Qnalist">
			<select name="searchKey">
				<option value="qna_title">제목</option>
				<option value="qna_content">글내용</option>
				<option value="userid">글쓴이</option>
			</select> <input type="text" name="searchWord" id="searchWord" /> <input
				type="submit" value="Search" />
		</form>
	</div>
</main>