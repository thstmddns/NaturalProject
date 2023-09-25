<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
    <h1>회원가입 폼</h1>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <!-- 개인정보를 보호해야하기 때문에 메소드는 post 방식으로 -->   
    <form method="post" action="<%=request.getContextPath() %>/register/registerJoinOk" onsubmit="return validateForm()">
        <ul>
            <li>아이디 : <input type="text" name="userid" id="userid"></li>
            <button type="button" id="dupChk">아이디 중복 확인</button>
            <li>비밀번호 : <input type="password" name="pwd" id="pwd"></li>
            <li>비밀번호 확인 : <input type="password" name="pwd2" id="pwd2"></li> 
            <li>이름 : <input type="text" name="username" id="username"></li>
            <li>이메일 : <input type="email" name="email" id="email"></li>
            <li>경력 : <input type="text" name="years" id="years"></li>
            <li>관련직무 : <input type="text" name="work" id="work"></li>
            <li>관심사 : <input type="text" name="concern" id="concern"></li>
            <li>해시태그 : <input type="text" name="hashtag" id="hashtag"></li>
            <li>회원유형 : <input type="text" name="usertype" id="usertype"></li>         
            <li><button type="submit">회원가입하기</button></li>
        </ul>
    </form>
    <div id="dupCheckResult"></div>

    <script>
    function validateForm() {
        var userid = $("#userid").val();
        var pwd = $("#pwd").val();
        var pwd2 = $("#pwd2").val();
        var username = $("#username").val();
        var email = $("#email").val();

        // 비밀번호 조건 정규식
        var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,25}$/;

        if (userid === "") {
            alert("아이디를 입력하세요.");
            return false;
        }
        if (pwd === "") {
            alert("비밀번호를 입력하세요.");
            return false;
        }
        
        if (!passwordPattern.test(pwd)) {
            alert("비밀번호는 영문자, 숫자, 특수문자 조합으로 8~25자리를 사용해야 합니다.");
            return false;
        }
        
		if (pwd2 === "") {
			alert("비밀번호 확인란를 입력하세요");
			return false;
		}
		

        if (pwd !== pwd2) {
            alert("비밀번호가 일치하지 않습니다.");
            return false;
        }
		
        if (username === "") {
            alert("이름을 입력하세요.");
            return false;
        }
		
		if (email === "") {
			alert("이메일을 입력하세요");
			return false;
		}
		
		if(document.getElementById("dochk").getAttribute("data-value") != "Y") {
			alert("아이디 중복검사를 해주세요.")
			return false;
		}

            return true; // 모든 검사를 통과하면 제출을 허용
        }

        $("#dupChk").on('click', function() {
            var id = $("#userid").val();
            console.log(id);
            $.ajax({
                data : {
                    'id' : id
                },
                url : '${pageContext.request.contextPath}/register/dupChk',
                success : function(result) {
                    console.log(result)
                    if (result == 1) {
                        $("#dupCheckResult").text("이미 사용중인 아이디입니다.").css('color', 'red');
                        $("#userid").val('');
                    } else {
                        $("#dupCheckResult").text("사용 가능한 아이디입니다.").css('color', 'blue');
                    }
                },
                error : function(e) {
                    console.log(e.responseText);
                }
            });
        });
    </script>
</main>
