<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main>
	<h1 style="font-weight:300; margin:40px 0 30px 0; background:white;">"<span class="keyword">키워드</span>" 검색 결과</h1>
	<div id="searchContainer">
		<div><a href='/ozz/main/searchMission?<c:if test="${pDTO.searchWord != null}">searchWord=${pDTO.searchWord}</c:if>'>미션(${pDTO.m_totalRecord})</a></div>
		<div><a href='/ozz/main/searchCom?<c:if test="${pDTO.searchWord != null}">searchWord=${pDTO.searchWord}</c:if>'>커뮤니티(${pDTO.q_totalRecord + pDTO.r_totalRecord + pDTO.b_totalRecord})</a></div>
		<div  class="select">마스터(${pDTO.u_totalRecord})</div>
	</div>
	<div id="searchAll">
		<div id="searchResultMaster">
		<c:if test="${empty U_list}">
			<div>${pDTO.searchWord}에 대한 마스터 검색 결과가 없습니다.</div>
			<li>직접 마스터가 되어보세요. <a href="#">회원가입하기</a></li>
		</c:if>
			<c:forEach var="U_dto" items="${U_list}">
			<div class="searchMa">
				<div><img src="<%= request.getContextPath()%>/img/응원사진.jpg"/></div>
				<div class="searchMaInfo">
					<li><span>${U_dto.username}</span><span>${U_dto.work}</span></li>
					<li><span>팔로우 100</span><span>팔로잉 100</span></li>
					<div>${U_dto.intro}</div>
				</div>
				<div><button>팔로우 하기</button></div>
			</div>
			<hr/>
			</c:forEach>
			
			<div class="searchMa">
				<div><img src="<%= request.getContextPath()%>/img/Frame 2.png"/></div>
				<div class="searchMaInfo">
					<li><span>임시완</span><span>가전제품 상품기획 DCX Analyst</span></li>
					<li><span>팔로우 100</span><span>팔로잉 100</span></li>
					<div>자기소개 칸</div>
				</div>
				<div><button>팔로우 하기</button></div>
			</div>
			<hr/>
			<div class="searchMa">
				<div><img src="<%= request.getContextPath()%>/img/Frame 2.png"/></div>
				<div class="searchMaInfo">
					<li><span>이준기</span><span>가전제품 상품기획 DCX Analyst</span></li>
					<li><span>팔로우 100</span><span>팔로잉 100</span></li>
					<div>자기소개 칸</div>
				</div>
				<div><button>팔로우 하기</button></div>
			</div>
			<hr/>
			<div class="searchMa">
				<div><img src="<%= request.getContextPath()%>/img/Frame 2.png"/></div>
				<div class="searchMaInfo">
					<li><span>아이유</span><span>가전제품 상품기획 DCX Analyst</span></li>
					<li><span>팔로우 100</span><span>팔로잉 100</span></li>
					<div>자기소개칸에 얼마나 써야 이 칸이 넘어가는지 실험 중입니다. 몇 줄이나 써야 생략이 될까요? 닭볶음탕 참 맛있었는데 정말 죽을 거 같네요
					계단이라도 올라야 할까봐요.. 제 위는 왜 점점 작아지는 걸까요 슬프네요 먹고싶은 건 참 많은데요ㅜㅜ 아니 대체 언제까지 얼마나 써야 이 글이 경계를 넘어가는 걸까요
					대표님이랑 상담하기 무섭네요 대체 무슨 말을 해야하는 거죠 집에 가고 싶어요 흐엉엉 집에 좀 보내주세요 제발 아니 얼마나 더 써야하는 거에요 힘들어죽겠네 정말 화딱지 나게 하지 말구
					얼른 보여줘 생략을 제발</div>
				</div>
				<div><button>팔로우 하기</button></div>
			</div>
			<hr/>
			<div class="searchMa">
				<div><img src="<%= request.getContextPath()%>/img/Frame 2.png"/></div>
				<div class="searchMaInfo">
					<li><span>송중기</span><span>가전제품 상품기획 DCX Analyst</span></li>
					<li><span>팔로우 100</span><span>팔로잉 100</span></li>
					<div>자기소개칸에 얼마나 써야 이 칸이 넘어가는지 실험 중입니다. 몇 줄이나 써야 생략이 될까요? 닭볶음탕 참 맛있었는데 정말 죽을 거 같네요
					계단이라도 올라야 할까봐요.. 제 위는 왜 점점 작아지는 걸까요 슬프네요 먹고싶은 건 참 많은데요ㅜㅜ 아니 대체 언제까지 얼마나 써야 이 글이 경계를 넘어가는 걸까요
					대표님이랑 상담하기 무섭네요 대체 무슨 말을 해야하는 거죠 집에 가고 싶어요 흐엉엉 집에 좀 보내주세요 제발 아니 얼마나 더 써야하는 거에요 힘들어죽겠네 정말 화딱지 나게 하지 말구
					얼른 보여줘 생략을 제발</div>
				</div>
				<div><button>팔로우 하기</button></div>
			</div>
			<hr/>
		</div>
			
		
		<div id="recommend">
			<div style="margin-bottom:10px;">추천 마스터</div>
			<div id="recommendMaContent">
				<li>홍길동</li>
				<li>가전제품 상품기획 DCX Analyst</li>
				<li><span>팔로워 100</span><span>팔로잉 100</span></li>
			</div>
			<div id="recommendMaContent">
				<li>홍길동</li>
				<li>가전제품 상품기획 DCX Analyst</li>
				<li><span>팔로워 100</span><span>팔로잉 100</span></li>
			</div>
			<div id="recommendMaContent">
				<li>홍길동</li>
				<li>가전제품 상품기획 DCX Analyst</li>
				<li><span>팔로워 100</span><span>팔로잉 100</span></li>
			</div>
		
		</div>
	</div>

</main>