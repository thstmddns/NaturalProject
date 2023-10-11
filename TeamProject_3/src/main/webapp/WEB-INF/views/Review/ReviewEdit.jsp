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

<main>
	<h1>리뷰수정</h1>
	<form method="post" action="/ozz/Review/ReviewEditOk">
	<input type="hidden" name="review_no" value="${dto.review_no}"/>
		<ul>
			<li>제목</li>
			<li><input type="text" name="review_title" id="review_title" value="${dto.review_title}"/></li>
			<li>리뷰점수</li>
			<li><input type="text" name="review_score" id="review_score" value="${dto.review_score}"/></li>
			<li>내용</li>
			<li><textarea name="review_content" id="review_content">${dto.review_content}</textarea></li>
			<!-- 여기 미션 입력하는 곳인데 해시태그나 이런거 어떻게할지 생각해보고 다시 해야함 ! ! ! -->
			<li>첨부파일</li>
			<li><input type="file" name="file_name" id="file_name" value="${dto.file_name}"/></li>
			
			<li><input type="submit" value="글 수정하기"/></li>
		</ul>
	</form>
</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("review_content"),option);
</script>