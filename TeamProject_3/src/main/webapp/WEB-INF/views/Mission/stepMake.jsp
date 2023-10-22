<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div>
			<button type="submit" form="missionMake" class="finish" >완료</button>
	</div>
	
	<form id="missionMake" action="" method="post">
	<div style="margin-top: 80px;">
			<input type="hidden" name="mission_no" value="${mission_no}">
			<li class="title" style="margin-bottom: 10px;">스텝</li>
			<ul id="step-list"></ul>
			<button class="plus" id="addStep" style="margin-bottom: 20px;">+  스텝 추가하기</button>
	</div>
		
		<div id="stepPlus">
			<li class="title">스텝 제목</li>
			<li><input type="text" name="step_title" placeholder="제목을 입력해주세요."></li>
			
			<div class="task">
				<li class="title">태스크 제목</li>
				<li><input type="text" name="task_title" placeholder="태스크 제목을 입력해주세요."></li>
				<li class="title" style="margin-bottom:0;">태스크 내용</li>
				<li><input type="text" name="task_content" placeholder="설명을 입력해주세요."></li>
				<button class="removeButton" style="background:white; border:none;"><img src="<%= request.getContextPath()%>/img/Variant5.jpg"/></button>
			</div>
			<button class="plus" id="addTask">+  태스크 추가하기</button>
		</div>
		</form>
</main>

<script>
	$(document).on("click", ".removeButton", function(){
	    if(confirm("태스크를 삭제하시겠습니까?")) {
		$(this).closest("div").remove();
	    }
	});
	
	$(document).ready(function(){
		$(document).on("click", "#addStep", function() {
			 event.preventDefault(); 
			
			var tag = "<div id='stepPlus' style='margin-top: 60px;'>";
				tag += "<li class='title'>스텝 제목</li>";
				tag += "<li><input type='text' name='' placeholder='제목을 입력해주세요.'></li>";
				tag += "<div class='task'>";
				tag += "<li class='title'>태스크 제목</li>";
				tag += "<li><input type='text' name='' placeholder='태스크 제목을 입력해주세요.''></li>";
				tag += "<li class='title' style='margin-bottom:0;'>태스크 내용</li>";
				tag += "<li><input type='text' name='' placeholder='설명을 입력해주세요.'></li>";
				tag += '<button class="removeButton" style="background:white; border:none;"><img src="<%= request.getContextPath()%>/img/Variant5.jpg"/></button></div>';
				tag += "<button class='plus'>+  태스크 추가하기</button>";
				
			$("#stepPlus").append(tag);
		})
	});
	
	$(document).ready(function(){
		$(document).on("click", "#addTask", function() {
			 event.preventDefault(); 
			
			var tag = "<div class='task' style='margin-top:20px;'>";
			tag += "<li class='title'>태스크 제목</li>";
			tag += "<li><input type='text' name='' placeholder='태스크 제목을 입력해주세요.''></li>";
			tag += "<li class='title' style='margin-bottom:0;'>태스크 내용</li>";
			tag += "<li><input type='text' name='' placeholder='설명을 입력해주세요.'></li>";
			tag += '<button class="removeButton" style="background:white; border:none;"><img src="<%= request.getContextPath()%>/img/Variant5.jpg"/></button></div>'
				
			$("#stepPlus").append(tag);
		})
	});


</script>