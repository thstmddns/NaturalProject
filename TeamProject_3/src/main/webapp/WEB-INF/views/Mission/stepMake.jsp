<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div>
			<input type="hidden" name="mission_no" value="${mission_no}">
			<li class="title" style="margin-bottom: 10px;">스텝</li>
			<ul id="step-list"></ul>
			<button class="plus" id="addStep" style="margin-bottom: 50px;">+  스텝 추가하기</button>
		</div>
		
		<div id="stepPlus">
			<li class="title">스텝 제목</li>
			<li><input type="text" name="" placeholder="제목을 입력해주세요."></li>
			
			<div class="task">
				<li class="title">태스크 제목</li>
				<li><input type="text" name="" placeholder="태스크 제목을 입력해주세요."></li>
				<li class="title" style="margin-bottom:0;">태스크 내용</li>
				<li><input type="text" name="" placeholder="설명을 입력해주세요."></li>
				<button class="removeButton" style="background:white; border:none;"><img src="<%= request.getContextPath()%>/img/Variant5.jpg"/></button>
			</div>
			<button class="plus" id="addTask">+  태스크 추가하기</button>
		</div>
</main>