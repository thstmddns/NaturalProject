<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
<h1 style="text-align:center; justify-content:center; align-items:center; margin:80px 0px 60px 0px">OZZ</h1>
<form method="post" class="logForm">
		<ul>
			<li>이메일</li>
			<li><input type="text" name="email" id="email" /></li>
			<li style="margin-top: 30px;">비밀번호</li>
			<li><input type="password" name="userpwd" id="userpwd"/></li>
			<li style="color: #869AAF; font-size:13px;  margin-top:10px;">*8자 이상이면서 최소한 숫자 하나와 글자 하나를 포함해야 합니다.<li>
			<li><input type="submit" value="로그인" class="registerBtn"/></li>
	</form>
			<li>
				<button class="registerKakao"><img src="<%= request.getContextPath() %>/img/ri_kakao-talk-fill.png" style="height:16px; vertical-align:text-bottom;"/>&nbsp&nbsp&nbsp카카오톡으로 로그인하기</button>
				<ul class="find_wrap" style="padding:10px;">
					<li>아이디 찾기</li>
					<li>비밀번호 찾기</li>
					<li>회원가입</li>
				</ul>
			</li>
		</ul>
</main>