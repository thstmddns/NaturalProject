<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	function delChk() {
	    //확인(true) 취소(false) 버튼
	    if (confirm("글을 삭제하시겠습니까?")) {
	       location.href="/ozz/Review/ReviewDel?no=${dto.review_no}";
	    }
	}
</script>

<main>
	<h1>리뷰 보기</h1>
	<div>
		<a href='/ozz/Review/Reviewlist?nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>목록</a>
	</div>
	<ul>
		<li>글번호 : ${dto.review_no}</li>
		<%-- <li>미션번호 : ${dto.mission_no}</li> --%>
		<li>글쓴이 : ${dto.userid}</li>
		<li>제목 : ${dto.review_title}</li>
		<li>점수 : ${dto.review_score}</li>
		<li>글내용<br/>
			${dto.review_content}</li>
		<li>등록일 : ${dto.created_at}</li>
		<li>파일내용 : ${dto.file_name}</li>
		<li>조회수 : ${dto.hit}</li>
	</ul>
	<div>
		<!-- session의 로그인 아이디(logId)와 현재 글쓴이(userid)가 같으면 수정,삭제 표시한다. -->
		<c:if test="${logId == dto.userid}">
			<a href="/ozz/Review/ReviewEdit?no=${dto.review_no}">수정</a>
			<a href="javascript:delChk()">삭제</a>
		</c:if>
	</div>
</main>