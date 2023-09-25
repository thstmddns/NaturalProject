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
	<form method="post" action="/ozz/Mission/MissionEditOk">
	<input type="hidden" name="mission_no" value="${dto.mission_no}"/>
		<ul>
			<li>제목</li>
			<li><input type="text" name="mission_title" id="mission_title" value="${dto.mission_title}"/></li>
			<li>글내용</li>
			<li><textarea name="mission_content" id="mission_content">${dto.mission_content}</textarea></li>
			
			<!-- 여기 미션 입력하는 곳인데 해시태그나 이런거 어떻게할지 생각해보고 다시 해야함 ! ! ! -->
			<li>미션태그</li>
			<li><input type="text" name="mission_tag" id="mission_tag" value="${dto.mission_tag}"/></li>
			<li>미션데이터</li>
			<li><input type="text" name="mission_data" id="mission_data" value="${dto.mission_data}"/></li>
			<li>사용 툴</li>
			<li><input type="text" name="mission_tool" id="mission_tool" value="${dto.mission_tool}"/></li>
			<li>카테고리</li>
			<li><input type="text" name="mission_cate" id="mission_cate" value="${dto.mission_cate}"/></li>
			
			<li><input type="submit" value="글 수정하기"/></li>
		</ul>
	</form>
</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("mission_content"),option);
</script>