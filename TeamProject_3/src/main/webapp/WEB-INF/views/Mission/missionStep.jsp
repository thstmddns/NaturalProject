<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div id="missionView">
		<div id="missionLeft">
			<div id="stepSub">
				<li><span>STEP 1</span>주피터 노트북 세팅</li>
			</div>
			
			<div id="learnSkill">
				<li>미션을 통해 습득할 <span style="color:#F93D18;">스킬</span></li>
				<li>이 미션을 수행하면 이런 스킬을 획득할 수 있어요!</li>
				<li><span>스킬 1</span><span>스킬 2</span><span>스킬 3</span><span>스킬 4</span><span>스킬 5</span><span>스킬 6</span></li>
			</div>
			
			<div id="require">
				<p style="font-size:1.2em;">미션을 시작하기 위한 <span style="color:#F93D18;">재료</span></p>
				<div class="ingredients">
					<div class="ingredient">
						<li>데이터<span>이런 파일 확장자를 사용합니다.</span></li>
						<div><span>csv</span><span>html</span><span>css</span></div>
					</div>
					<div class="ingredient">
						<li>Tool<span>이런 툴을 사용합니다. </li></p>
						<div><span>엑셀</span><span>워드</span></div>
					</div>
				</div>
			</div>

			<div id="stepPractice">		
				<div class="missionStep">
					<div class="StepDetail">
						<li style="font-size:1.2em;">지시 따르기<span>완료  <img src="<%= request.getContextPath()%>/img/체크.png"/></span></li>
						<div>
							같은 살았으며, 피는 풍부하게 위하여서. 행복스럽고 능히 몸이 것이다. 긴지라 노래하며 위하여, 피어나는 원대하고, 이것이다. 
	얼마나 이상 이것을 그것은 심장은 이상의 있음으로써 속잎나고, 사막이다. (더미 텍스트)
						</div>
					</div>
					<div class="StepDetail">
						<li style="font-size:1.2em;">주피터 노트북을 실행<span>완료  <img src="<%= request.getContextPath()%>/img/체크.png"/></span></li>
						<div>상세 절차를 확인합니다.</div>
						<li class="edit">수정하기</li>
					</div>
					<div class="StepDetail">
						<li style="font-size:1.2em;">주피터 노트북 스크립트 다운로드<span>완료  <img src="<%= request.getContextPath()%>/img/체크.png"/></span></li>
						<div>사전 체크를 위한 주피터 노트북 스크립트를 다운로드 합니다.</div>
						<li class="edit">수정하기</li>
					</div>
					<div class="StepDetail">
						<li style="font-size:1.2em;">파일 업로드<span>완료  <img src="<%= request.getContextPath()%>/img/체크.png"/></span></li>
						<div>스크립트 파일을 업로드 합니다.</div>
						<li class="edit">수정하기</li>
						<button class="next">다음 단계로 넘어가기↓</button>
					</div>
					<div class="StepDetail">
						<li style="font-size:1.2em;">텍스트 작성하기<span>진행 중</span></li>
						<div>(텍스트 작성에 대한 설명)</div>
						<li class="edit">수정하기</li>
					</div>
					<%-- <c:if test="${}"> --%>
					<button class="noComplete" disabled>완료하고 스텝 제출하기</button>
					<%-- </c:if> --%>
				<%-- 	<c:if test="${}"> --%>
					<button class="yesComplete">완료하고 스텝 제출하기</button>
					<%-- </c:if> --%>
				</div>
			</div>
		</div>	
			
			<c:if test="${completed.at=='Y'}">
			<div id="missionComplete">
				<li>Persona(페르소나) 분석</li>
				<li><span style="color:#F93D18;">제출</span> 완료되었습니다.</li>
				<div><img src="<%= request.getContextPath()%>/img/7.png"/></div>
				<li>제출한 미션을 커뮤니티에 공유해 함께 의션을 나누어 보세요.</li>
			</div>
			</c:if>
			
	
		<div id="missionRight">
			<div id="stepSubmit">
				<li>미션 스텝 제출 여부</li>
				<li>제출 안 함</li>
				<li>미션 스텝  제출 일시</li>
				<li>-</li>
				<li>피드백 현황<span>-</span></li>
			</div>
			
			<div>
			<p>Persona(페르소나) 분석</p>
			<div id="totalStep">
				<li>1.  전처리 데이터 가져오기</li>
				<li>2.  주피터 노트북 세팅</li>
				<li>3.  분석할 코드 만들기</li>
				<li>4.  파라메터 조정</li>
				<li>5.  코드 실행</li>
			</div>
		
		</div>
		
			<div id="missionBtn2" style="padding-right: 20px;">
				<button>미션 추천하기 <img src="<%= request.getContextPath()%>/img/Thumbs-up.png"/></button>		
			</div>
			
		</div>
		
		
		
	</div>
	




</main>