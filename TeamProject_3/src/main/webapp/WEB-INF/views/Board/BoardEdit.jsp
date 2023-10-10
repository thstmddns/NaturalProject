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
	<form method="post" action="/ozz/Board/BoardEditOk">
	<input type="hidden" name="board_no" value="${dto.board_no}"/>
		<ul>
			<li>제목</li>
			<li><input type="text" name="board_title" id="board_title" value="${dto.board_title}"/></li>
			<li>직무카테고리</li>
			<li><input type="text" name="work_cate" id="work_cate" value="${dto.work_cate}"/></li>
			<li>글내용</li>
			<li><textarea name="board_content" id="board_content">${dto.board_content}</textarea></li>
			<!-- 여기 미션 입력하는 곳인데 해시태그나 이런거 어떻게할지 생각해보고 다시 해야함 ! ! ! -->
			<li>첨부파일</li>
			<li><input type="text" name="file_name" id="file_name" value="${dto.file_name}"/></li>
			
			<li><input type="submit" value="글 수정하기"/></li>
		</ul>
	</form>
</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("board_content"),option);
</script>