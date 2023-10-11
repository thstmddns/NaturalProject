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

<style>
.M_Qna_list, .page>ul {
	overflow: auto;
}

.M_Qna_list>li {
	float: left;
	height: 40px; line-height =40px;
	border-bottom: 1px solid #ddd;
	width: 10%;
}

.M_Qna_list>li:nth-child(5n+2) {
	width: 60%;
	/*말 줄임 표시*/
	white-space: nowrap; /*줄바꾸지 않기*/
	overflow: hidden; /*넘친 값 숨기기*/
	text-overflow: ellipsis; /* ...표시하기*/
}

.M_Review_list, .page>ul {
	overflow: auto;
}

.M_Review_list>li {
	float: left;
	height: 40px; line-height =40px;
	border-bottom: 1px solid #ddd;
	width: 10%;
}

.M_Review_list>li:nth-child(6n+2) {
	width: 50%;
	/*말 줄임 표시*/
	white-space: nowrap; /*줄바꾸지 않기*/
	overflow: hidden; /*넘친 값 숨기기*/
	text-overflow: ellipsis; /* ...표시하기*/
}
</style>

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
		<li>미션 설명<br/>
			${dto.mission_content}</li>
		<li>미션 데이터 : ${dto.mission_data}</li>
		<li>사용 툴 : ${dto.mission_tool}</li>
		<li>사용 스킬 : ${dto.mission_skill}</li>
		<li>카테고리 : ${dto.mission_cate}</li>
		
 		<ul>
			<li><a href="/ozz/Step/Stepwrite?m_no=${dto.mission_no}" style="color: red; font-weight: bold;">STEP 리스트를 출력하고 글쓰기가 나와야함</a></li>
       		<c:forEach var="StepDTO" items="${Steplist}">
       			<c:if test="${dto.mission_no eq StepDTO.mission_no}">
					<li>해당스텝 : ${StepDTO.step}</li>
					<li><a href="/ozz/Step/StepView?no=${StepDTO.step_no}">스텝제목 : ${StepDTO.step_title}</a></li>
					<li>소요시간 : ${StepDTO.step_time}</li>
				</c:if>
       		</c:forEach>
		</ul>
		<li>조회수 : ${dto.hit}</li>
		
		<li><a href="/ozz/Qna/Qnawrite?m_no=${dto.mission_no}" style="color: red; font-weight: bold;">QnA</a></li>

		<!-- 미션에 해당하는 QnA 등록 및 출력 -->
		<ul class="M_Qna_list">
		<li>QnA 번호</li>
		<li>QnA 제목</li>
		<li>QnA 작성자</li>
		<li>QnA 작성일자</li>
		<li>조회수</li>
		<c:forEach var="QnaDTO" items="${M_Qnalist}">
			<c:if test="${dto.mission_no eq QnaDTO.mission_no}">
				<li>${QnaDTO.qna_no}</li>
				<li><a href="/ozz/Qna/QnaView?no=${QnaDTO.qna_no}">${QnaDTO.qna_title}</a></li>
				<li>${QnaDTO.userid}</li>
				<li>${QnaDTO.created_at}</li>
				<li>${QnaDTO.hit}</li>
			</c:if>
		</c:forEach>
		</ul>
		
		<!-- 미션에 해당하는 Review 등록 및 출력 -->
		<li><a href="/ozz/Review/Reviewwrite?m_no=${dto.mission_no}" style="color: red; font-weight: bold;">Review</a></li>
		<ul class="M_Review_list">
		<li>Review 번호</li>
		<li>Review 제목</li>
		<li>Review 점수</li>
		<li>Review 작성자</li>
		<li>Review 작성일자</li>
		<li>조회수</li>
		<c:forEach var="ReviewDTO" items="${M_Reviewlist}">
			<c:if test="${dto.mission_no eq ReviewDTO.mission_no}">
				<li>${ReviewDTO.review_no}</li>
				<li><a href="/ozz/Review/ReviewView?no=${ReviewDTO.review_no}">${ReviewDTO.review_title}</a></li>
				<li>${ReviewDTO.review_score}</li>
				<li>${ReviewDTO.userid}</li>
				<li>${ReviewDTO.created_at}</li>
				<li>${ReviewDTO.hit}</li>
			</c:if>
		</c:forEach>
		</ul>
		
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