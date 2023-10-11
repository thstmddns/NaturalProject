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
	<h1>스텝 글수정</h1>
	<form method="post" action="/ozz/Task/TaskEditOk">
	<input type="hidden" name="task_no" value="${dto.task_no}"/>
	<input type="hidden" name="step_no" value="${dto.step_no}"/>
		<ul>
			<li>제목</li>
			<li><input type="text" name="task_title" id="task_title" value="${dto.task_title}"/></li>
			<li>내용</li>
			<li><textarea name="task_content" id="task_content">${dto.task_content}</textarea></li>
			<li>첨부파일</li>
			<li><input type="file" name="file_name" id="file_name" value="${file_name}"/></li>
		
			<li><input type="submit" value="글 수정하기"/></li>
		</ul>
	</form>
</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("task_content"),option);
</script>