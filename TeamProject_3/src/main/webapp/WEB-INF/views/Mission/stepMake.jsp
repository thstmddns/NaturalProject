<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>
	<form id="result" enctype="multipart/form-data" method="POST"
		action="${pageContext.request.contextPath}/Mission/steptaskWriteOk">
		<div>
			<button type="submit" form="result" class="finish">완료</button>
		</div>
		<!-- 각 스텝과 태스크가 동적으로 추가될 공간 -->
		<input type="hidden" name="mission_no" value="${mission_no }" />
		<li class="title" style="margin-bottom: 10px; margin-top: 150px;">스텝</li>
		<button id="addStep" class="plus" style="margin-bottom: 100px;">+
			스텝 추가하기</button>
		<!-- 스텝 추가 버튼 -->

		<div class="step-container">
			<li class="title" style="margin-bottom: 10px;">스텝 제목</li> <input
				type="text" placeholder="제목을 입력해주세요." class="step-title">

			<div class="task-container">
				<div class="task-info">
					<button class="remove-task"
						style="background: white; border: none; float: right;">
						<img src="<%=request.getContextPath()%>/img/Variant5.jpg" />
					</button>
					<li class="title">태스크 제목</li> <input type="text"
						placeholder="태스크 제목을 입력해주세요." class="task-title" name="task_title" />
					<li class="title" style="margin-bottom: 0;">태스크 내용</li> <input
						type="text" class="task_content" placeholder="설명을 입력해주세요."
						name="task_content"> <input type="file" class="task-file"
						name="file_name" />

				</div>
				<div class="task-info">
					<button class="remove-task"
						style="background: white; border: none; float: right;">
						<img src="<%=request.getContextPath()%>/img/Variant5.jpg" />
					</button>
					<li class="title">태스크 제목</li> <input type="text"
						placeholder="태스크 제목을 입력해주세요." class="task-title" name="task_title" />
					<li class="title" style="margin-bottom: 0;">태스크 내용</li> <input
						type="text" class="task_content" placeholder="설명을 입력해주세요."
						name="task_content"> <input type="file" class="task-file"
						name="file_name" />
				</div>
			</div>
			<button class="add-task">+ 태스크 추가하기</button>
			<button class="remove-step">스텝 삭제</button>
		</div>
	</form>



</main>

<script>
   $(document).ready(
         function() {
            // 스텝 추가 코드
            $(document).on("click", "#addStep", function() {
            	 event.preventDefault();
                     // 스텝 전체를 담을 div 생성
                     var stepContainer = $("<div>").addClass(
                           "step-container");
                     
                     // 별 의미 없음 그냥 스텝이라고 구분할 단어
                     var stepLabel = $("<li>").text("스텝 제목");

                     // 스텝 이름
                     var stepInput = $("<input>").attr({
                        type : "text",
                        placeholder : "스텝 이름"
                     }).addClass("step-title");

                     // 태스크 여러개 담을 div
                     var taskContainer = $("<div>").addClass(
                           "task-container");
                     
                     // 스텝 컨테이너에 담기
                     stepContainer.append(stepLabel, stepInput,
                           taskContainer);
                     
                     // 태스크 추가 버튼, 스텝 제거 버튼
                     var addTaskButton = $("<button>").text("+  태스크 추가하기")
                           .addClass("add-task");
                     var removeStepButton = $("<button>").text("스텝 삭제")
                           .addClass("remove-step");

                     stepContainer.append(addTaskButton,
                           removeStepButton);

                     removeStepButton.click(function() {
                        stepContainer.remove();
                     });
                        
                     // 추가된 스텝 컨테이너 폼에 추가
                     $("#result").append(stepContainer);
                  });
            
            // 태스크 추가 버튼 눌렀을 떄
            $(document).on("click", ".add-task", function() {
                     event.preventDefault(); // 기본 동작 방지
                     event.preventDefault(); // 기본 동작 방지
                     
                     // 태스크 컨테이너에 태스크가 여러개 있어야 하므로 구분될 div 하나 더 생성
                     var taskUnderContainer = $("<div>").addClass("task-info");
					
                     var taskLabel = $("<li>").text("태스크 제목");
                     
                     // 태스크 이름
                     var taskName = $("<input>").attr({
                         type: "text",
                         placeholder: "태스크 제목을 입력해주세요."
                     }).addClass("task-title");

                     // 태스크 예시
                     var taskExample = $("<input>").attr({
                    	 type: "text",
                         placeholder: "설명을 입력해주세요."
                     }).addClass("task_content").text("");

                     // 태스크 파일
                     var taskFile = $("<input>").attr({
                         type: "file",
                         placeholder: "단계"
                     }).addClass("task-file");
                     
                     // 태스크 삭제
                     // 태스크 삭제 버튼을 생성하고 이미지를 추가
				    var removeTaskButton = $("<button>").addClass("remove-task").css({
				        background: "white",
				        border: "none",
				        float: "right"
				    });
				
				    var removeTaskImage = $("<img>").attr("src", "<%=request.getContextPath()%>/img/Variant5.jpg");
				    removeTaskButton.append(removeTaskImage);

                     taskUnderContainer.append(removeTaskButton, taskName, taskExample, taskFile);
                     $(this).parent().find(".task-container").append(taskUnderContainer);

                  });
            // 스텝 삭제 버튼
            $(document).on("click", ".remove-step", function() {
               event.preventDefault(); // 기본 동작 방지
               $(this).parent().remove();
            });
         
            // 태스크 삭제 버튼
            $(document).on("click", ".remove-task", function() {
               event.preventDefault(); // 기본 동작 방지
               $(this).parent().remove();
            });
            
            // 버튼 눌렀을 때
            $(document).on("click", ".finish", function() {
                event.preventDefault(); // 기본 동작 방지
                updateIndexes(); // 폼을 제출할 때 인덱스 업데이트

                var form = $(this).closest('form'); // 현재 버튼이 속한 폼을 가져옴
                var formData = new FormData(form[0]); // FormData 객체 생성

                $.ajax({
                    type: form.attr('method'), // 폼의 method를 가져옴 (일반적으로 POST)
                    url: form.attr('action'), // 폼의 action 속성에 지정된 URL로 요청을 보냄
                    data: formData, // FormData 객체를 전송
                    contentType: false, // 필수: false로 설정하여 content type을 자동 설정하지 않도록 함
                    processData: false, // 필수: false로 설정하여 data를 문자열로 변환하지 않도록 함
                    success: function(response) {
                        alert("등록되었습니다.");
                        window.location.replace("${pageContext.request.contextPath}/");
                    },
                    error: function(xhr, status, error) {
                        alert("등록에 실패했습니다. 오류 메시지: " + xhr.responseText); // 실패 메시지 띄우기
                    }
                });
            });
            
            // 폼에 존재하는 태그들 인덱스 name에 추가
            function updateIndexes() {
                $(".step-container").each(function(stepIndex) {
                    $(this).find(".step-title").attr("name", "steps[" + stepIndex + "].step_title");
                    $(this).find(".step-time").attr("name", "steps[" + stepIndex + "].step_time");

                    $(this).find(".task-info").each(function(taskIndex) {
                        $(this).find(".task-title").attr("name", "steps[" + stepIndex + "].tasks[" + taskIndex + "].task_title");
                        $(this).find(".task_content").attr("name", "steps[" + stepIndex + "].tasks[" + taskIndex + "].task_content");
                        $(this).find(".task-file").attr("name", "steps[" + stepIndex + "].tasks[" + taskIndex + "].task_file_name");
                    });
                });
            }
         });
</script>
<script>
	<%-- $(document).on("click", ".removeButton", function(){
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
	
	 --%>


</script>