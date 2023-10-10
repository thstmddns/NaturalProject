<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- jQuery 라이브러리를 로드합니다. -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
    <h1>아이디 찾기</h1>
    <form method="post" id="findId">
        <ul>
            <li>이름 : <input type="text" name="username" id="username"/></li>
            <li>이메일 : <input type="text" name="email" id="email"/></li>
            <li><input type="button" value="아이디 찾기" id="findIdButton"/></li>
        </ul>
    </form>
    
    <!-- 아이디 결과를 표시할 영역 -->
    <div id="result"></div>
    
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
</body>
</html>