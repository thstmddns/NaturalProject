<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div id="landing">
	    <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 20px; display: inline-flex;">
	        <div style="margin-top: 10px;"><span style="color: #222A2F; font-size: 30px; word-wrap: break-word;">어떻게 일 할까 고민될 땐,<br/></span><span style="color: #222A2F; font-size: 40px; word-wrap: break-word">워크레시피 Ozz!</span></div>
	            <button class="landBtn">회원가입하기</button>
	    </div>
	    <div id="landingImage" style="display:flex; margin-left:90px;">
	        <div style="position:absolute"><img src="<%= request.getContextPath() %>/img/Ellipse.png" style="width: 115.45px; height: 115.45px;"/>
	        <img src="<%= request.getContextPath() %>/img/Chart.png" style="width: 120.45px; height: 90.98px; margin-left: 40px;"/></div>
	        <div style="position:relative;"><span><img src="<%= request.getContextPath() %>/img/Woman.png" style="width: 130px; height: 195px;"/>
	        <img src="<%= request.getContextPath() %>/img/Chart 1.png"  style="width: 90.45px; height: 60.98px;"/>
	        <img src="<%= request.getContextPath() %>/img/Man.png" style="width: 140px; height: 185px;"/></span></div>
	    </div>
	</div>
	<div class="search"><input type="text" id="searchLand" placeholder="지금 어떤 업무 도움이 필요하신가요?"/>
	<button style="width: 10%; height: 42px; top: 0; bottom: 0; right: 5px; color: white; background-color: #52B0C5; border: hidden; border-radius: 13px; justify-content:center;">검색하기</button></div>
	<div id="landInfo">
		 <div>
		 	<li style="font-size:2em; margin-bottom: 10px;">0</li>
		 	<li>미션 수</li>
		 </div>
		 <div>
		 	<li style="font-size:2em; margin-bottom: 10px;">0</li>
		 	<li>수행된 미션 수</li>
		 </div>
		 <div>
		 	<li style="font-size:2em; margin-bottom: 10px;">0</li>
		 	<li>회원 수</li>
		 </div>
	</div>
	<div id="landImage2">
		<div>
		<img src="<%= request.getContextPath()%>/img/5.png" style="width:400px; height: 300px;"/>
		</div>
		<div style="margin-top:30px;">
			<div>
			<li style="font-size:1.3em;"><i class="fa-solid fa-check" style="color: #52B0C5;"></i>&nbsp&nbsp당신이 일할 때 꼭 필요한 워크레시피!</li>
			<li style="color:#5a5a5a; margin: 20px 0 0 25px">게임하는 것처럼 일하고, 일하면서 배울 수 있습니다.
				</br>업무에 대해 단계별로 알려드리고 미션을 수행할 수 있도록 도와드립니다. 
				</br>실무 프로세스 미션을 수행하며 업무 성장을 이루어 보세요!</li>
			</div>
			<div style="margin-top: 30px;">
			<li style="font-size:1.3em;"><i class="fa-solid fa-check" style="color: #52B0C5;"></i>&nbsp&nbsp누군가는 당신과 같은 일을 하고 있지 않을까요?</li>
			<li style="color:#5a5a5a; margin: 20px 0 0 25px">당신이 하고 있는 일의 가장 비슷한 레퍼런스를 확인하세요.
				</br>모르는 것은 랜선사수에게 바로 물어보세요.
				</br>실제 업무처럼 미션을 수행하고 랜선 사수와 소통하고 피드백을 받을 수 있습니다.</li>
			</div>
		</div>
	</div>
	<div>
		<div id="popM">
			<li style="font-size:1.6em">주간 인기 미션 Top 10</li>
			<li style="color: #869AAF; margin-top:5px; font-size:0.9em">지금 가장 많이 도전하고 있는 미션</li>
		</div>
		<div id="popMList">
			<div>01</div>
			<div></div>
			<div>
			<li>고객 경험 디자인</li>
			<li>태그</li>
			<li>홍길동</li>
			</div>
		</div>
	
	
	</div>
	
</main>