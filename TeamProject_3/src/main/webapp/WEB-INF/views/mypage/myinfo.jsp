<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.logForm li:nth-child(2n){
		margin-bottom: 40px;
	}
	.logForm li:nth-child(2n+1){
		font-size: 1.0em !important;
	}
	.noChange {
		width: 500px;
		height: 32px;
		border-radius: 6px; 
		border: 1px #DAE2EB solid;
	}
	.editOk {
		width: 500px;
		height: 32px;
		border-radius: 6px;
		color: white;
		background: #52B0C5;
		border: none;
		margin: 30px 0 0 10px;
	}

</style>
<main>
	<%-- 페이지 내 어딘가에 아래와 같이 실패 메시지를 표시 --%>
	<c:if test="${not empty errorMessage}">
	    <script>
	        alert("${errorMessage}");
	    </script>
	</c:if>
    <h1 style="text-align:center; margin: 80px 0 50px 0;">프로필 수정하기</h1>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <form method="post" action="<%=request.getContextPath() %>/mypage/updateOk" onsubmit="return validateForm()" class="logForm">
        <ul>
            <li>아이디</li>
            <li class="noChange"><input type="hidden" name="userid" id="userid" value="${dto.userid}"> ${dto.userid}</li>
            <li style="margin-left: 10px;">비밀번호</li> 
            <li><input type="password" name="pwd" id="pwd"></li>
            <li>비밀번호 확인</li>
            <li><input type="password" name="pwd2" id="pwd2"></li> 
            <li>이름</li>
            <li class="noChange"><input type="hidden" name="username" id="username" value="${dto.username}">  ${dto.username}</li>
            <li>이메일 </li>
            <li><input type="email" name="email" value="${dto.email}" /></li>
            <li>경력</li> 
            <li><input type="text" name="years" value="${dto.years}" /></li>
            <li>관련직무</li>
            <li><select class="select" name="work" id="work" title="직무를 선택해주세요" style="width:100%; height:40px; border-radius: 6px; border: 1px #DAE2EB solid; color:#6e6e6e">
			    <option value="${dto.work}">${dto.work}</option>
			    <option value="경영·사무">경영·사무</option>
			    <option value="무역·유통">무역·유통</option>
			    <option value="영업·고객상담">영업·고객상담</option>
			    <option value="서비스">서비스</option>
			    <option value="마케팅·광고·홍보">마케팅·광고·홍보</option>
			    <option value="IT·인터넷">IT·인터넷</option>
			    <option value="디자인">디자인</option>
			    <option value="생산·제조">생산·제조</option>
			</select></li>
            <li>관심사</li>
            <li><select class="select" name="concern" id="concern" title="관심사를 선택해주세요" style="width:100%; height:40px; border-radius: 6px; border: 1px #DAE2EB solid; color:#6e6e6e">
			    <option value="${dto.concern}">${dto.concern}</option>
			    <option value="Python"># Python</option>
			    <option value="JavaScript"># JavaScript</option>
			    <option value="Spring"># Spring</option>
			    <option value="React"># React</option>
			    <option value="Kotlin"># Kotlin</option>
			    <option value="Docker"># Docker</option>
			</select></li>
			<li>자기소개</li> 
            <li><textarea name="intro" id="intro" value="${dto.intro}"/>${dto.intro}</textarea></li>
        <button type="submit" class="editOk">수정하기</button>
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
		
		if (email === "") {
			alert("이메일을 입력하세요");
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

