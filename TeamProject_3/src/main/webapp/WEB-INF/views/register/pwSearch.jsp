<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
<h1 style="text-align:center; justify-content:center; align-items:center; margin:80px 0px 60px 0px">비밀번호 찾기</h1>
<form method="post" class="logForm">
		<ul>
			<li>아이디</li>
			<li><input type="text" name="userid" id="userid" /></li>
			<li style="margin: 30px 0 0 10px;">이메일</li>
			<li><input type="text" name="email" id="email"/></li>
			<li><input type="button" value="비밀번호 찾기" class="registerBtn" id="findIdBtn"/></li>
	</form>
			<li>
				<ul class="find_wrap" style="padding:10px;">
					<li><a href="/ozz/register/login">로그인</a></li>
					<li><a href="/ozz/register/registerJoin">회원가입</a></li>
				</ul>
			</li>
		</ul>
</main>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	$("#findIdBtn").on("click", function(){
		let userid = document.getElementById("userid").value;
		let email = document.getElementById("email").value;
		if (userid == "" || userid == null) {
			alert("아이디를 입력해주세요")
			return false;
		}
		if (email == "" || email == null) {
			alert("이메일을 입력해주세요")
			return false;
		}
		
		$.ajax({
			data:{
				'userid':userid,
				'email':email
			},
			method:'POST',
			url:'${pageContext.request.contextPath}/register/findPwOk',
			
			success:function(result){
				if (result == 0) {
					alert("입력하신 정보와 일치하는 회원이 없습니다")
					console.log(result);
				} else {
					alert("이메일로 비밀번호가 전송되었습니다.")
					window.location.href = "${pageContext.request.contextPath}/register/login";
				}
			},
			error:function(e){
				alert("이메일 전송 실패")
				console.log(e.responseText);
			}
		})
	})
</script>