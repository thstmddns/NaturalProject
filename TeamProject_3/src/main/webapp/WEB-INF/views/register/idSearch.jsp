<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
<h1 style="text-align:center; justify-content:center; align-items:center; margin:80px 0px 60px 0px">아이디 찾기</h1>
<form method="post" class="logForm">
		<ul>
			<li>이름</li>
			<li><input type="text" name="name" id="name" /></li>
			<li style="margin: 30px 0 0 10px;">이메일</li>
			<li><input type="text" name="email" id="email"/></li>
			<li><input type="submit" value="아이디 찾기" class="registerBtn"/></li>
	</form>
			<li>
				<ul class="find_wrap" style="padding:10px;">
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</li>
		</ul>
</main>