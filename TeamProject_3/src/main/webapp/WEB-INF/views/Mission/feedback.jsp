<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/super-build/ckeditor.js"></script>
<script src="/ozz/inc/ckeditor.js"></script>

<style>
	
	.ck-editor__editable[role="textbox"] {/* editing area */
	   min-height: 400px;
	}
	.ck-content .image {/* block images */
		max-width: 80%;
		margin: 20px auto;
	}

</style>

<main>
	<div id="writeTop">
		<span style="color:#4E657E; font-size:16px; margin-top:10px;"><img src="<%= request.getContextPath()%>/img/__.png" style="vertical-align:middle;"/>&nbsp;피드백</span>
		<span style="float:right;" id="writeBtn">
			<button>완료</button>
		</span>
	</div>
	<form id="feedbackWriteForm" style="width:500px;">
		<ul>
			<input type="hidden" name="mission_no" value="${dto.no }">
			<div class="taskSubject">
				<li>미션 명</li>
				<li>제출일 2023.09.25</li>	
			</div>
			<li style="font-size: 18px; margin-top: 60px;">태스크 제출자</li>		
			<div class="taskSubmit">
				<div><img src="<%= request.getContextPath()%>/img/Group 7210.png"/></div>
				<div class="taskSubmitInfo">
					<li>홍길동<span style="margin-left:15px;">가전제품 상품기획 DCX Analyst</span></li>
					<li><span>팔로워 100</span><span>팔로잉 100</span></li>
				</div>
			</div>
			<li style="margin: 60px 0 20px 0; font-size: 18px; margin-top: 60px;">완료한 태스크</li>
			<div class="taskComplete">
				<div>지시 따르기 (업로드나 작성이 없는 태스크)<span>완료&nbsp;<img src="<%= request.getContextPath()%>/img/체크.png"/></span></div>
				<div>링크 이동하기<span>완료&nbsp;<img src="<%= request.getContextPath()%>/img/체크.png"/></span></div>
				<div>파일 다운로드 하기<span>완료&nbsp;<img src="<%= request.getContextPath()%>/img/체크.png"/></span></div>
				<div>파일 업로드 하기<span>완료&nbsp;<img src="<%= request.getContextPath()%>/img/체크.png"/></span></div>
				<div>텍스트 작성하기<span>완료&nbsp;<img src="<%= request.getContextPath()%>/img/체크.png"/></span></div>
			</div>
			<li style="font-size: 18px; margin-bottom: 10px">점수 입력</li>
			<li class="sub">제출자가 미션 스텝을 잘 수행했나요? 미션 수행에 대한 점수를 입력해 주세요.</li>
			<li><input type="text" style="width:70px; margin-bottom: 60px;" name="feedback_score" id="feedback_score" placeholder="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;10">&nbsp;&nbsp;&nbsp;점</li>
			<li style="font-size: 18px; margin-bottom: 10px">피드백 입력</li>
			<li class="sub">좋았던 점이나 개선해야 할 점들을 알려주면 제출자에게 도움이 될거에요.</li>
			<li><textarea name="feedback_content" id="feedback_content"></textarea></li>
		</ul>
	
	</form>

</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("content"), option);
</script>