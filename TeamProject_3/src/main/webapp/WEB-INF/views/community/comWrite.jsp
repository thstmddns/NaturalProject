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
		<span style="color:#4E657E; font-size:16px; margin-top:10px;"><img src="<%= request.getContextPath()%>/img/__.png" style="vertical-align:middle;"/>&nbsp;커뮤니티로 돌아가기</span>
		<span style="float:right;" id="writeBtn">
			<button>보관</button>
			<button>완료</button>
		</span>
	</div>
	<form id="comWriteForm" style="width:500px;">
		<ul>
			<li style="font-size: 18px;">제목</li>
			<li style="margin: 10px 0 60px 0;"><input type="text" name="subject"  placeholder="&nbsp;&nbsp;&nbsp;제목을 입력해주세요."/></li>
			<li style="font-size: 18px;">카테고리</li>		
			<div id="cate">
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">개발·프로그래밍</li>
					<li><input type="checkbox">웹 개발</li>
					<li><input type="checkbox">프론트엔드</li>
					<li><input type="checkbox" >백엔드</li>
					<li><input type="checkbox">풀스택</li>
					<li><input type="checkbox">데이터베이스</li>
				</div>
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">보안·네트워크</li>
					<li><input type="checkbox">보안</li>
					<li><input type="checkbox">네트워크</li>
					<li><input type="checkbox">시스템</li>
					<li><input type="checkbox">클라우드</li>
					<li><input type="checkbox">블록체인</li>
				</div>
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">데이터 사이언스</li>
					<li><input type="checkbox">데이터 분석</li>
					<li><input type="checkbox">인공지능</li>
					<li><input type="checkbox">데이터 시각화</li>
					<li><input type="checkbox">데이터 수집·처리</li>
					<li><input type="checkbox">자격증</li>
				</div>
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">하드웨어</li>
					<li><input type="checkbox">컴퓨터 구조</li>
					<li><input type="checkbox">반도체</li>
					<li><input type="checkbox">로봇공학</li>
					<li><input type="checkbox">모빌리티</li>
					<li><input type="checkbox">자격증</li>
				</div>
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">비즈니스·마케팅</li>
					<li><input type="checkbox">오피스</li>
					<li><input type="checkbox">마케팅</li>
					<li><input type="checkbox">업무 자동화</li>
					<li><input type="checkbox">경영</li>
					<li><input type="checkbox">자격증</li>
				</div>	
			</div>
			<li style="font-size: 18px; margin-bottom: 15px">내용 입력</li>
			<li><textarea name="content" id="content"></textarea></li>
		</ul>
	
	</form>

</main>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("content"), option);
</script>