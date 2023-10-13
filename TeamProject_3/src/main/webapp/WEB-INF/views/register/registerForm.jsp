<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	#dupChk {
		border: none;
		color: white;
		background: #2B94AB;
		padding: 5px 10px;
		border-radius: 16px;
		margin-left: 10px;
	}


</style>


<main>
	<h1 style="text-align:center; justify-content:center; align-items:center; margin:80px 0px 30px 0px">환영합니다!</h1>
	<div style="text-align: center; color: #4E657E; font-size: 14px; font-weight: 400; word-wrap: break-word; margin-bottom:50px">오즈에서 실무 능력을 향상시켜보세요.<br/>아래에 있는 간단한 질문 몇 가지에 답해주시면<br/>길동님 관심사에 맞춘 정보를 제공해드릴 수 있어요.</div>
	<form method="post" class="logForm" action="<%=request.getContextPath() %>/register/registerJoinOk" onsubmit="return validateForm()">
		<ul>
			<li>아이디</li>
			<li><input type="text" name="userid" id="userid" /></li>
			<button type="button" id="dupChk">아이디 중복 확인</button>
			<div id="dupCheckResult" style="margin-top: 10px;"></div>
			<li style="margin: 30px 0 0 10px; font-size: 1.0em;">이메일</li>
			<li><input type="text" name="email" id="email" /></li>
			<li style="margin-top: 30px; font-size: 16px;">비밀번호</li>
			<li><input type="password" name="pwd" id="pwd"/></li>
			<li style="color: #869AAF; font-size:13px; margin-top:10px;">*8자 이상이면서 영어, 숫자, 특수기호를 포함하여야 합니다.<li>
			<li style="margin-top: 30px;">비밀번호 확인</li>
			<li><input type="password" name="pwd2" id="pwd2"/></li>
			<li style="color: #869AAF; font-size:13px;  margin-top:10px;">*비밀번호를 입력해주세요.<li>
			<li style="margin-top: 30px;">이름</li>
			<li><input type="text" name="username" id="username" /></li>
			<li style="margin-top: 30px;">몇 년 차이신가요?</li>
			<li><input type="text" name="years" id="years" placeholder="&nbsp;&nbsp;연차를 입력해주세요."/></li>
			<li style="margin-top: 30px;">어떤 직무를 맡고 계신가요?</li>
			<li><select class="select" name="work" id="work" title="직무를 선택해주세요" style="width:100%; height:40px; border-radius: 6px; border: 1px #DAE2EB solid; color:#6e6e6e">
			    <option value="">직무를 선택해주세요</option>
			    <option value="경영·사무">경영·사무</option>
			    <option value="무역·유통">무역·유통</option>
			    <option value="영업·고객상담">영업·고객상담</option>
			    <option value="서비스">서비스</option>
			    <option value="마케팅·광고·홍보">마케팅·광고·홍보</option>
			    <option value="IT·인터넷">IT·인터넷</option>
			    <option value="디자인">디자인</option>
			    <option value="생산·제조">생산·제조</option>
			</select></li>
			<li style="margin-top: 30px;">어떤 업무에 관심을 갖고 계신가요?</li>
			<li style="color: #869AAF; font-size:13px;  margin-top:13px;">관심 있는 주제를 선택해서 오즈를 맞춤 설정해보세요.<li>
			<li><select class="select" name="concern" id="concern" title="관심사를 선택해주세요" style="width:100%; height:40px; border-radius: 6px; border: 1px #DAE2EB solid; color:#6e6e6e">
			    <option value="">관심사를 선택해주세요</option>
			    <option value="Python"># Python</option>
			    <option value="Java"># Java</option>
			    <option value="JavaScript"># JavaScript</option>
			    <option value="Vue"># Vue</option>
			    <option value="Spring"># Spring</option>
			    <option value="React"># React</option>
			    <option value="Kotlin"># Kotlin</option>
			    <option value="Docker"># Docker</option>
			</select></li>
			<li style="margin-top: 30px;">나를 간단히 소개해봐요 !</li>
			<li style="color: #869AAF; font-size:13px;  margin-top:13px;">마스터들에게 나를 소개해봐요.<li>
			<li><input type="text" name="intro" id="intro" /></li>
			<li><input type="submit" value="회원가입" class="registerBtn"/></li>
	</form>
	<form method="post" action="/register/kakaoJoinOk">
			<li>
				<button type="submit" class="registerKakao"><img src="<%= request.getContextPath() %>/img/ri_kakao-talk-fill.png" style="height:16px; vertical-align:text-bottom;"/>&nbsp&nbsp&nbsp카카오톡으로 회원가입하기</button>
			</li>
	</form>
	

</main>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
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
