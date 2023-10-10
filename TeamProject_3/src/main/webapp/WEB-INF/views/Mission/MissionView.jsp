<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	function delChk() {
	    //확인(true) 취소(false) 버튼
	    if (confirm("글을 삭제하시겠습니까?")) {
	       location.href="/ozz/Mission/MissionDel?no=${dto.mission_no}";
	    }
	}
</script>
<main>
	<h1>미션내용보기</h1>
	<div>
		<a href='/ozz/Mission/Missionlist?nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>목록</a>
	</div>
	<ul>
		<li>글번호 : ${dto.mission_no}</li>
		<li>글쓴이 : ${dto.userid}</li>
		<li>제목 : ${dto.mission_title}</li>
		<li>관심태그 : ${dto.mission_tag}</li>
		<li>등록일 : ${dto.created_at}</li>
		<li>글내용<br/>
			${dto.mission_content}</li>
		<li>미션 데이터 : ${dto.mission_data}</li>
		<li>사용 툴 : ${dto.mission_tool}</li>
		<li>카테고리 : ${dto.mission_cate}</li>
		
 		<ul>
			<li><a href="/ozz/Step/Stepwrite?m_no=${dto.mission_no}" style="color: red; font-weight: bold;">STEP 리스트를 출력하고 글쓰기가 나와야함</a></li>
       		<c:forEach var="StepDTO" items="${Steplist}">
				<li>해당스텝 : ${StepDTO.step}</li>
				<li>스텝제목 : ${StepDTO.step_title}</li>
				<li>소요시간 : ${StepDTO.step_time}</li>
				<li>스텝스킬 : ${StepDTO.mission_skill}</li>
       		</c:forEach>
		</ul>
		
		<li>조회수 : ${dto.hit}</li>
	</ul>
	<div>
		<!-- session의 로그인 아이디(logId)와 현재 글쓴이(userid)가 같으면 수정,삭제 표시한다. -->
		<c:if test="${logId == dto.userid}">
			<a href="/ozz/Mission/MissionEdit?no=${dto.mission_no}">수정</a>
			<a href="javascript:delChk()">삭제</a>
		</c:if>
	</div>


	<!-- 댓글달기 -->
	<style>
		#coment{width:500px; height:80px;}
		#replyList>li{border-bottom:1px solid #ddd; padding:5px 0px}
	</style>
</main>