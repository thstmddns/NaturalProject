<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/super-build/ckeditor.js"></script>
<script src="/ozz/inc/ckeditor.js"></script>
<style>
	.ck-editor__editable[role="textbox"] {
	    /* editing area */
	    min-height: 200px;
	}
	.ck-content .image {
	    /* block images */
	    max-width: 80%;
	    margin: 20px auto;
	}
</style>

<!-- 파일첨부 테스트 -->
<!-- <script type="text/javascript">
   function convertImageToBase64(event) {
     const fileInput = event.target;
     const file = fileInput.files[0];
     const reader = new FileReader();

     reader.onload = function (event) {
       const base64 = event.target.result;
       document.getElementById("file_name_base64_input").value = base64;
     };

     reader.readAsDataURL(file);
   }
</script> -->

<main>
	<h1>리뷰쓰기</h1>
	<form method="post" action="/ozz/Review/ReviewwriteOk">
		<ul>
			<li><input type="hidden" name="mission_no" id="mission_no" value="${m_no}" /></li>
			<li>제목</li>
			<li><input type="text" name="review_title" id="review_title"/></li>
			<li>리뷰점수</li>
			<li><input type="text" name="review_score" id="review_score"></input>점</li>
			<li>내용</li>
			<li><textarea name="review_content" id="review_content"></textarea></li>
			<li>첨부파일</li>
			<li><input type="file" name="file_name" id="file_name"/></li>
			
<!-- 			<li>
            	<div id='imglist'>
            		<input type="file" name="file_name" id="file_name" onchange="convertImageToBase64(event);" />
            			base64로 변환된 이미지 데이터를 저장하기 위한 숨겨진 input 필드 추가
            		<input type="hidden" name="file_name_base64" id="file_name_base64_input" />
            	</div>
      		</li> -->
			
			<li><input type="submit" value="글등록"/></li>
		</ul>
	</form>
</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("review_content"),option);
</script>