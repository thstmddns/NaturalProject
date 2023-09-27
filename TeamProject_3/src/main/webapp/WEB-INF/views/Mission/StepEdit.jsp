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
	<form method="post" action="/ozz/Step/StepEditOk">
	<input type="hidden" name="step_no" value="${dto.step_no}"/>
		<ul>
			<li>스텝</li>
			<li><input type="text" name="step" id="step" value="${dto.step}"/></li>
			<li>스텝제목</li>
			<li><input type="text" name="step_title" id="step_title" value="${dto.step_title}"/></li>
			<li>스텝내용</li>
			<li><textarea name="step_content" id="step_content">${dto.step_content}</textarea></li>
			<li>소요시간</li>
			<li><input type="text" name="step_time" id="step_time" value="${dto.step_time}"/></li>
			<li>미션스킬</li>
			<li><input type="text" name="mission_skill" id="mission_skill" value="${dto.mission_skill}"/></li>
			
			<li><input type="submit" value="글 수정하기"/></li>
		</ul>
	</form>
</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("step_content"),option);
</script>