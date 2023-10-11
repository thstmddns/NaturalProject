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
	<h1>게시판 글쓰기</h1>
	<form method="post" action="/ozz/Mission/MissionwriteOk">
		<ul>
			<li>미션 제목</li>
			<li><input type="text" name="mission_title" id="mission_title"/></li>
			<li>미션 설명</li>
			<li><textarea name="mission_content" id="mission_content"></textarea></li>
			
			<!-- 여기 미션 입력하는 곳인데 해시태그나 이런거 어떻게할지 생각해보고 다시 해야함 ! ! ! -->
			<li>미션 태그</li>
			<li><input type="text" name="mission_tag" id="mission_tag"/></li>
			<li>사용 데이터</li>
			<li><input type="text" name="mission_data" id="mission_data"/></li>
			<li>사용 툴</li>
			<li><input type="text" name="mission_tool" id="mission_tool"/></li>
			<li>사용 스킬</li>
			<li><input type="text" name="mission_skill" id="mission_skill"/></li>
			<li>카테고리</li>
			<li><input type="text" name="mission_cate" id="mission_cate"/></li>
			
			
			<!-- 스텝 입력 -->
			
			
<!-- 			<li>
            	<div id='imglist'>
            		<input type="file" name="file_name" id="file_name" onchange="convertImageToBase64(event);" />
            			base64로 변환된 이미지 데이터를 저장하기 위한 숨겨진 input 필드 추가
            		<input type="hidden" name="file_name_base64" id="file_name_base64_input" />
            	</div>
      		</li> -->
			
			<li><input type="submit" value="미션등록"/></li>
		</ul>
	</form>
</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("mission_content"),option);
</script>