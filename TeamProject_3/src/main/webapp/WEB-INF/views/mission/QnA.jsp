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
		<span style="color:#4E657E; font-size:16px; margin-top:10px;"><img src="<%= request.getContextPath()%>/img/__.png" style="vertical-align:middle;"/>&nbsp;미션으로 돌아가기</span>
		<span style="float:right;" id="writeBtn">
			<button>보관</button>
			<button>완료</button>
		</span>
	</div>
	<form id="QnAWriteForm" style="width:500px;">
		<ul>
			<li class="subject">미션 명</li>
			<li style="font-size: 18px; margin-top: 60px;">QnA 제목</li>		
			<li style="margin: 15px 0 60px 0;"><input type="text" name=""  placeholder="&nbsp;&nbsp;&nbsp;제목을 입력해주세요."/></li>
			<li style="font-size: 18px; margin-bottom: 15px">QnA 내용 입력</li>
			<li><textarea name="" id="content"></textarea></li>
		</ul>
	
	</form>

</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("content"), option);
</script>