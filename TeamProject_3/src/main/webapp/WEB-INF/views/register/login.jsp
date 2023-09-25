<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
   function logChk(){
      //아이디 존재 유무 확인
      if(document.getElementById("userid").value==""){
         alert("아이디를 입력하세요.");
         return false;
      }
      if(document.getElementById("pwd").value==""){
         alert("비밀번호를 입력하세요.");
         return false;
      }
      return true;
   }
</script>
<main>
   <h1>로그인 폼</h1>
   <form method="post" action="/ozz/register/loginOk" onsubmit="return logChk()">
      <ul>
         <li><input type="text" name="userid" id="userid" placeholder="아이디입력"/></li>
         <li><input type="password" name="pwd" id="pwd" placeholder="비밀번호입력"/></li>
         <li><input type="submit" value="로그인"/></li>
         <li>
            <!-- 이름과 연락처를 입력받아 DB에서 아이디와 이메일을 조회 후
                아이디를 메일로 보낸다. -->
            <div><a href="/ozz/register/findId">아이디찾기</a></div>
            <div><a href="/ozz/register/findPw">비밀번호찾기</a></div>
            <div><a href="/ozz/register/registerJoinOk">회원가입</a></div>
         </li>
      </ul>
   </form>
</main>