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
	<h1>게시판 글수정</h1>
	<form method="post" action="/ozz/Qna/QnaEditOk">
	<input type="hidden" name="qna_no" value="${dto.qna_no}"/>
		<ul>
			<li>제목</li>
			<li><input type="text" name="qna_title" id="qna_title" value="${dto.qna_title}"/></li>
			<li>글내용</li>
			<li><textarea name="qna_content" id="qna_content">${dto.qna_content}</textarea></li>
			<li><input type="submit" value="글 수정하기"/></li>
		</ul>
	</form>
</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("qna_content"),option);
</script>