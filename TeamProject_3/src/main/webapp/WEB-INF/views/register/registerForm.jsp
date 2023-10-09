<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<h1 style="text-align:center; justify-content:center; align-items:center; margin:80px 0px 30px 0px">환영합니다!</h1>
	<div style="text-align: center; color: #4E657E; font-size: 14px; font-weight: 400; word-wrap: break-word; margin-bottom:50px">오즈에서 실무 능력을 향상시켜보세요.<br/>아래에 있는 간단한 질문 몇 가지에 답해주시면<br/>길동님 관심사에 맞춘 정보를 제공해드릴 수 있어요.</div>
	<form method="post" class="logForm">
		<ul>
			<li>아이디</li>
			<li><input type="text" name="userid" id="userid" /></li>
			<li style="margin: 30px 0 0 10px;">이메일</li>
			<li><input type="text" name="email" id="email" /></li>
			<li style="margin-top: 30px; font-size: 16px;">비밀번호</li>
			<li><input type="password" name="userpwd" id="userpwd"/></li>
			<li style="color: #869AAF; font-size:13px; margin-top:10px;">*8자 이상이면서 최소한 숫자 하나와 글자 하나를 포함해야 합니다.<li>
			<li style="margin-top: 30px;">비밀번호 확인</li>
			<li><input type="password" name="userpwd" id="userpwd"/></li>
			<li style="color: #869AAF; font-size:13px;  margin-top:10px;">*비밀번호를 입력해주세요.<li>
			<li style="margin-top: 30px;">이름</li>
			<li><input type="text" name="name" id="name" /></li>
			<li style="margin-top: 30px;">몇 년 차이신가요?</li>
			<li><input type="text" name="year" id="year" placeholder="&nbsp;&nbsp;연차를 입력해주세요."/></li>
			<li style="margin-top: 30px;">어떤 직무를 맡고 계신가요?</li>
			<li><select class="select" title="직무를 선택해주세요" style="width:100%; height:40px; border-radius: 6px; border: 1px #DAE2EB solid; color:#6e6e6e">
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
			<li style="margin-top: 30px;">키워드</li>
			<div class="keywordTag">
			<button id="keyword"># javascript</button>
			<button id="keyword"># react</button>
			<button id="keyword"># java</button>
			<button id="keyword"># python</button>
			<button id="keyword"># c, c++</button>
			<button id="keyword"># typescript</button>
			</br><button id="keyword" style="margin-top:10px"># next.js</button>
			</div>
			<li><input type="submit" value="회원가입" class="registerBtn"/></li>
	</form>
			<li>
				<button class="registerKakao"><img src="<%= request.getContextPath() %>/img/ri_kakao-talk-fill.png" style="height:16px; vertical-align:text-bottom;"/>&nbsp&nbsp&nbsp카카오톡으로 회원가입하기</button>
			</li>
		






</main>