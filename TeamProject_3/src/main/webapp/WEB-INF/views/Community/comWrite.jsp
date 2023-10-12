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
		<span style="color:#4E657E; font-size:16px; margin-top:10px;"><a href="javascript:window.history.back();"><img src="<%= request.getContextPath()%>/img/__.png" style="vertical-align:middle;"/>&nbsp;커뮤니티로 돌아가기</a></span>
		<span style="float:right;" id="writeBtn">
			<button>보관</button>
			<button type="submit" form="comWriteForm">완료</button>
		</span>
	</div>
	<form id="comWriteForm" style="width:500px;" method="post" action="/ozz/Board/BoardwriteOk" onsubmit="return validate();">
		<ul >
			<li style="font-size: 18px; margin-bottom: 20px;">게시글 분류</li>
			<li style="margin-bottom: 40px;"><select class="select" name="board_cate" id="board_cate" title="분류를 선택해주세요" style="width:100%; height:40px; border-radius: 6px; border: 1px #DAE2EB solid; color:#6e6e6e">
			    <option value="">분류를 선택해주세요</option>
			    <option value="태스크">태스크</option>
			    <option value="자유 게시글">자유 게시글</option>
			</select></li>
			<li style="font-size: 18px;">제목</li>
			<li style="margin: 10px 0 60px 0;"><input type="text" name="board_title" id="board_title" placeholder="&nbsp;&nbsp;&nbsp;제목을 입력해주세요." onclick="return false;"/></li>
			<li><input type="text" style="display:none;"></li>
			<li style="font-size: 18px; margin-bottom:10px;">카테고리</li>		
			<div id="cate">
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">개발·프로그래밍</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="웹 개발">웹 개발</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="프론트엔드">프론트엔드</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="백엔드">백엔드</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="풀스택">풀스택</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="데이터베이스">데이터베이스</li>
				</div>
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">보안·네트워크</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="보안">보안</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="네트워크">네트워크</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="시스템">시스템</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="클라우드">클라우드</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="블록체인">블록체인</li>
				</div>
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">데이터 사이언스</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="데이터 분석">데이터 분석</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="인공지능">인공지능</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="데이터 시각화">데이터 시각화</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="데이터 수집·처리">데이터 수집·처리</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="자격증">자격증</li>
				</div>
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">하드웨어</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="컴퓨터 구조">컴퓨터 구조</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="반도체">반도체</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="로봇공학">로봇공학</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="모빌리티">모빌리티</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="자격증">자격증</li>
				</div>
				<div>
					<li style="font-size:1.0em; margin-bottom:5px; color:#6e6e6e;">비즈니스·마케팅</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="오피스">오피스</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="마케팅">마케팅</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="업무 자동화">업무 자동화</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="경영">경영</li>
					<li><input type="checkbox" name="work_cate" id="work_cate" value="자격증">자격증</li>
				</div>	
			</div>	
			
		<div>
			<li style="font-size: 18px; margin-bottom: 15px">내용 입력</li>
			<li><textarea name="board_content" id="board_content" onclick="return false;"></textarea></li>
		</div>

			

		</ul>
	
	</form>

</main>
<script>
		 CKEDITOR.ClassicEditor.create(document.getElementById("board_content"), option);
		 CKEDITOR.config.autoParagraph = false;
		
		function validate() {
			
		
		  var board_title = document.getElementById("board_title").value;
		  var board_content = CKEDITOR.instances.board_content.getData();
		
		  if (board_title.trim() === "") {
		    alert("제목을 입력하세요.");
		    return false;
		  }
		
		  if (board_content.trim() === "") {
		    alert("내용을 입력하세요.");
		    return false;
		  }
		
		  // 추가적인 유효성 검사 로직을 여기에 추가할 수 있습니다.
		
		  // 모든 검사가 통과하면 true를 반환하여 폼을 제출합니다.
		  return true;
		}
		
</script>
	