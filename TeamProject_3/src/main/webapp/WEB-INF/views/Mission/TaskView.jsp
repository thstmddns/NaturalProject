<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	function delChk() {
	    //확인(true) 취소(false) 버튼
		if (confirm("글을 삭제하시겠습니까?")) {
		    location.href = '/ozz/Task/TaskDel?no=' + ${dto.task_no} + '&step_no=' + ${dto.step_no}
		};
	}
	
	</script>
<main>
	<h1>태스크내용보기</h1>
	<ul>
		<li>제목 : ${dto.task_title}</li>
		<li>내용<br/>
			${dto.task_content}</li>
	</ul>
	<div>
		<!-- session의 로그인 아이디(logId)와 현재 글쓴이(userid)가 같으면 수정,삭제 표시한다. -->
		<c:if test="${logId == dto.userid}">
			<a href="/ozz/Task/TaskEdit?no=${dto.task_no}">수정</a>
			<a href="javascript:delChk()">삭제</a>
		</c:if>
	</div>
</main>