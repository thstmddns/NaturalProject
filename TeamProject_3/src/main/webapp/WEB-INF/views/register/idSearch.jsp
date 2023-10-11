<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
<h1 style="text-align:center; justify-content:center; align-items:center; margin:80px 0px 60px 0px">아이디 찾기</h1>
<form method="post" class="logForm" id="findId">
		<ul>
			<li>이름</li>
			<li><input type="text" name="username" id="username" /></li>
			<li style="margin: 30px 0 0 10px;">이메일</li>
			<li><input type="text" name="email" id="email"/></li>
			<li><input type="button" value="아이디 찾기" class="registerBtn" id="findIdButton"/></li>
	</form>
	
	<div id="result"></div>
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
        $(function(){
            $("#findIdButton").click(function(){
                $.ajax({
                    url:'/ozz/register/findIdOk',
                    data:{
                        username:$("#username").val(),
                        email:$("#email").val()
                    },
                    type:"post",
                    success:function(result){
                        if(result == "Y"){
                            alert("아이디가 이메일로 보내졌습니다.");
                            location.href="/ozz/register/login";                        
                        }else{
                            alert("일치하는 정보가 존재하지 않습니다");
                        }
                    },error:function(){
                        console.log("아이디 찾기 에러");
                    }
                });
            });
        });
    </script>