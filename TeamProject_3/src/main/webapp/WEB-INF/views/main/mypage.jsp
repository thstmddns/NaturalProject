<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div>
		<div id="myInfo">
			<div id="myInfoDetail">
				<div><img src="<%= request.getContextPath()%>/img/Group 7210.png"/></div>
				<div class="myInfoDetail2">
					<li>홍길동<span>가전제품 상품기획 DCX Analyst</span></li>
					<li>팔로워 100<span>팔로잉 100</span></li>
					<li>새로운 기술과 트렌드를 습득하고 적용하는 것을 즐기는 개발자입니다. 
						<br/>함께 일 하는 동료와 지식을 공유하며 성장하는 것이 저의 목표입니다.</li>
				</div>
			</div>
			<div class="career">
				<li>회사명<span>포지션</span><span>2000.00.00 ~ 현재까지</span></li>
				<li>회사명<span>프론트엔드 개발자</span><span>2000.00.00 ~ 0000.00.00</span></li>
				<li>회사명<span>백엔드 개발자</span><span>2000.00.00 ~ 0000.00.00</span></li>
				<li style="float:right; font-size:0.8em;">더보기 ></li>		
			</div>	
		</div>
		<div id="myInfoBottom">
			<div id="pageLeft">
				<div id="pageButton">
					<button>구독 내역</button>
					<button>프로필 수정하기</button>
				</div>
				<div id="skill">
					<li># 직무 스킬</li>
					<li><span>태그</span><span>태그</span><span>태그</span><span>태그</span><span>태그</span><span>태그</span></li>
					<li># 업무 스킬</li>
					<li><span>태그</span><span>태그</span><span>태그</span><span>태그</span><span>태그</span></li>
					<li># 툴 스킬</li>
					<li><span>태그</span><span>태그</span><span>태그</span></li>
				</div>
				<div id="missionTotal">
					<li>미션 수행 통계</li>
					<li>완료한 미션<span>10</span></li>
					<li>진행 중인 미션<span>10</span></li>
				</div>
				<div id="missionGo">
					<li>진행 중인 미션<span>더보기 ></span></li>
					<li>Persona(페르소나) 분석 미션</li>
					<li>진행률<span>20%</span><span>남은 스텝 수</span><span>00</span></li>
					<li>프로그래스바</li>
					<li><button>이어서 진행하기</button></li>
				</div>			
			</div>
			<div id="pageRight">
			</div>
		</div>


	</div>




</main>