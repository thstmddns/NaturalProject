<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	function delChk() {
	    //확인(true) 취소(false) 버튼
		if (confirm("글을 삭제하시겠습니까?")) {
		    location.href = '/ozz/Step/StepDel?no=' + ${dto.step_no} + '&mission_no=' + ${dto.mission_no}
		};
	}
	
	function delChk2(task_no, step_no) {
	    if (confirm("글을 삭제하시겠습니까?")) {
	        location.href = '/ozz/Task/TaskDel?no=' + task_no + '&step_no=' + step_no;
	    }
	}
</script>
	
<main>
	<h1>스텝내용보기</h1>
	<ul>
		<li>스텝 : ${dto.step}</li>
		<li>제목 : ${dto.step_title}</li>
		<li>소요시간 : ${dto.step_time}</li>
		<li>작성자 : ${dto.userid}</li>
		<ul>
		<li><a href="/ozz/Task/Taskwrite?s_no=${dto.step_no}" style="color: red; font-weight: bold;">태스크 글쓰기</a></li>

		<c:forEach var="TaskDTO" items="${Tasklist}">
			<c:if test="${dto.step_no eq TaskDTO.step_no}">
				<%-- <li><a href="/ozz/Task/TaskView?no=${TaskDTO.task_no}">태스크제목 : ${TaskDTO.task_title}</a></li> --%>
				<li>태스크제목 : ${TaskDTO.task_title}</li>
				<li>태스크내용 : ${TaskDTO.task_content}</li>
				<li>첨부파일 : ${TaskDTO.file_name}</li>
				<button><a href="/ozz/Task/TaskEdit?no=${TaskDTO.task_no}">수정</a></button>
				<button><a href="javascript:delChk2(${TaskDTO.task_no}, ${TaskDTO.step_no})">삭제</a></button>
			</c:if>
       	</c:forEach>
		</ul>
		
	</ul>
	<div>
		<!-- session의 로그인 아이디(logId)와 현재 글쓴이(userid)가 같으면 수정,삭제 표시한다. -->
		<c:if test="${logId == dto.userid}">
			<a href="/ozz/Step/StepEdit?no=${dto.step_no}">수정</a>
			<a href="javascript:delChk()">삭제</a>
		</c:if>
	</div>
</main>