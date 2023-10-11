<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <title>카카오 SDK 사용 예제</title>
    <!-- 카카오 SDK 스크립트 로드 -->
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>

	<script>
	    function logChk() {
	        // 아이디와 비밀번호를 체크하는 로직 추가
	        if (document.getElementById("userid").value === "") {
	            alert("아이디를 입력하세요.");
	            return false;
	        }
	        if (document.getElementById("pwd").value === "") {
	            alert("비밀번호를 입력하세요.");
	            return false;
	        }
	        return true;
	    }
	</script>
  
<body>    
<main>
  	<h1 style="text-align:center; justify-content:center; align-items:center; margin:80px 0px 60px 0px">OZZ</h1>
   <form method="post" action="/ozz/register/loginOk" onsubmit="return logChk()" class="registerForm">
      <ul>
		 <li>아이디</li>
         <li><input type="text" name="userid" id="userid" /></li>
         <li style="margin-top: 30px; margin-left:10px;">비밀번호</li>
         <li><input type="password" name="pwd" id="pwd"/></li>
         <li><input type="submit" class="registerBtn" value="로그인"/></li>
		
         <li>
			<button class="registerKakao"><img src="<%= request.getContextPath() %>/img/ri_kakao-talk-fill.png" style="height:16px; vertical-align:text-bottom;"/>&nbsp&nbsp&nbsp카카오톡으로 로그인하기</button>
            <!-- 이름과 연락처를 입력받아 DB에서 아이디와 이메일을 조회 후
                아이디를 메일로 보낸다. -->
           <ul class="find_wrap" style="padding:10px;">
               <li><a href="/ozz/register/findId" class="item">아이디 찾기</a></li>
               <li><a href="/ozz/register/findPw" class="item">비밀번호 찾기</a></li>
               <li><a href="/ozz/register/registerJoin">회원가입</a></li>
            </ul>

         </li>
      </ul>
       
   </form>

</main>
</body>
</html>