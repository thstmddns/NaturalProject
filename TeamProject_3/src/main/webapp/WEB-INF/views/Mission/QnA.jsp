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
	#imglist {
		margin-top: 60px;
	}
	#upload-name {
    display: inline-block;
    height: 40px;
    padding: 0 20px;
    vertical-align: middle;
    border: 1px solid #dddddd;
    width: 78%;
    color: #999999;
    border-radius: 10px;
	}
	#imglist label {
    display: inline-block;
    padding: 10px 20px;
    color: #fff;
    vertical-align: middle;
    background-color: #999999;
    cursor: pointer;
    height: 20px;
    margin-left: 10px;
    width: 80px;
    border-radius: 10px;
    text-align: center;
}

	#imglist input[type="file"] {
    display: none;
}
</style>

<main>
	<div id="writeTop">
		<span style="color:#4E657E; font-size:16px; margin-top:10px;"><a href="/ozz/main/mainMission"><img src="<%= request.getContextPath()%>/img/__.png" style="vertical-align:middle;"/>&nbsp;미션으로 돌아가기</a></span>
		<span style="float:right;" id="writeBtn">
			<button>보관</button>
			<button type="submit" form="QnAWriteForm">완료</button>
		</span>
	</div>
	<form id="QnAWriteForm" style="width:500px;" method="post" action="/ozz/Qna/QnawriteOk">
		<ul>
			<input type="hidden" name="mission_no" id="mission_no" value="${m_no}">
			<li class="subject">${mission_title}</li>
			<li style="font-size: 18px; margin-top: 60px;">QnA 제목</li>		
			<li style="margin: 15px 0 60px 0;"><input type="text" name="qna_title" id="qna_title" placeholder="&nbsp;&nbsp;&nbsp;&nbsp;제목을 입력해주세요."/></li>
			<li style="font-size: 18px; margin-bottom: 15px">QnA 내용 입력</li>
			<li><textarea name="qna_content" id="qna_content"></textarea></li>
			<li>
            	<div id='imglist'>
            		<input id="upload-name" value="" placeholder="첨부파일">
				    <label for="file_name">파일찾기</label> 
            		<input type="file" name="file_name" id="file_name" onchange="convertImageToBase64(event);" />
            			<!-- base64로 변환된 이미지 데이터를 저장하기 위한 숨겨진 input 필드 추가 -->
            		<input type="hidden" name="file_name_base64" id="file_name_base64_input" />
            	</div>
      		</li>
		</ul>
	
	</form>

</main>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	CKEDITOR.ClassicEditor.create(document.getElementById("qna_content"), option);
	CKEDITOR.config.autoParagraph = false;
	
	$("#file_name").on('change',function(){
		   var fileName = $("#file_name").val();
		   $("#upload-name").val(fileName);
	});
	   
</script>

<script type="text/javascript">
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
   
  
</script>