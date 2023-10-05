<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div id="missionView">
		<div id="missionLeft">
			<div id="missionInfo">
				<li>Persona(페르소나) 분석</li>
				<li><span>상품기획</span><span>분석론</span><span>데이터</span></li>
				<div>수 많은 데이터가 축적되고 데이터를 활용하는 인공지능 기술도 좋아지면서 고객의 관심과 니즈를 파악하는 맥락적 고객 명험의 시대가 왔습니다. 한 명의 고객에게도 시간과 장소를 같이 하는 사람에 따라 맥락을 다르게 봐야하는 시대입니다. 이 이션은 고객 데이터를 딥러닝 기반 클러스터링(Clustering, 군집화) 기법을 통해 다양한 마이크로 세그먼트로 분류합니다. 빅데이터로 여러 다양한 페르소나를 한꺼번에 찾아내고, 각가의 맥락에 대해 데이터가 리얼하게 보여주며 수치로 증명하여 객관성을 갖도록 합니다.</div>
				<li>소요시간<span>8시간</span><span>카테고리</span><span>상품기획</span></li>
			</div>
			
			<div id="missionMaster">
				<li>이 미션의 랜선 <span style="color:#F93D18;">사수</span></li>
				<li style="font-size:1.2em;">홍길동<span>가전제품 상품기획 DCX Analyst</span></li>
				<li>팔로워<span>100</span><span>팔로잉</span><span>100</span></li>
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
		
			<div style="margin: 80px 0 20px 0; font-size: 1.5em;">미션 실행하기</div>
			
			<div id="missionComplete">
				<li>Persona(페르소나) 분석</li>
				<li><span style="color:#F93D18;">제출</span> 완료되었습니다.</li>
				<div><img src="<%= request.getContextPath()%>/img/7.png"/></div>
				<li>제출한 미션을 커뮤니티에 공유해 함께 의션을 나누어 보세요.</li>
			</div>
			
			<div id="missionReview" style="width:90%;">
				<div style="margin-top: 80px; font-size: 1.5em;">리뷰 (3)<span style="font-size: 0.7em; color:#869AAF; float:right;">더보기 ></span></div>
					<div class="missionReviewExam">
						<li>홍길동<span>2000-00-00</span><span>평점: 4.5점</span></li>
						<div class="reviewText">같은 살았으며, 피는 풍부하게 위하여서. 행복스럽고 능히 몸이 것이다. 긴지라 노래하며 위하여, 피어나는 원대하고, 이것이다. 얼마나 이상 이것을 그것은 심장은 이상의 있음으로써 속잎나고, 사막이다. (더미 텍스트)</div>
					</div>
			</div>
			
			
			<div id="missionQnA">
				<div style="margin-top: 80px; font-size: 1.5em;">Q&A</div>
				<div id="QnAContent">
					<button>질문이 있으신가요? <span style="text-decoration:underline;">Q&A 작성하기</span></button>
					<div class="QnADetail">
						<li>[질문] 백엔드 코드를 아름답게 분리하고 싶어요</li>
						<li><span>Q&A</span><span>질문</span><span>태그</span></li>
						<li>홍길동<span>2000-00-00</span></li>
						<c:if  test="${logStatus=='Y'}">
						<input type="textarea" placeholder="  ↳  답변 달기">
						</c:if>
					</div>
					<div class="QnADetail">
						<li>[질문] 리액트 파일 업로드 관련</li>
						<li><span>Q&A</span><span>질문</span><span>태그</span></li>
						<li>홍길동<span>2000-00-00</span></li>
						<c:if  test="${logStatus=='Y'}">
						<input type="textarea" placeholder="  ↳  답변 달기">
						</c:if>
					</div>
					<div class="QnADetail">
						<li>[질문] 리액트 파일 업로드 관련</li>
						<li><span>Q&A</span><span>질문</span><span>태그</span></li>
						<li>홍길동<span>2000-00-00</span></li>
						<c:if  test="${logStatus=='Y'}">
						<input type="textarea" placeholder="  ↳  답변 달기">
						</c:if>
					</div>
				</div>
			</div>
		</div>
	
		<div id="missionRight">
			<div id="date">
				<li>등록일<span>22.00.00</span></li>
				<li>시작일<span>22.00.00</span></li>
				<li>완료일<span>22.00.00</span></li>
				<li>진행률<span>20%</span></li>
			</div>
		
			<div id="missionBtn">
				<button>미션 추천하기 <img src="<%= request.getContextPath()%>/img/Thumbs-up.png"/></button>
				<button>미션 공유하기</button>			
			</div>
		</div>
		
		
	</div>
	




</main>