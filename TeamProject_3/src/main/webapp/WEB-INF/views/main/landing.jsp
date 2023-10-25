<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
	<div id="landing">
	    <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 20px; display: inline-flex;">
	        <div style="margin-top: 10px;"><span style="color: #222A2F; font-size: 30px; word-wrap: break-word;">어떻게 일 할까 고민될 땐,<br/></span><span style="color: #222A2F; font-size: 40px; word-wrap: break-word">워크레시피 Ozz!</span></div>
	            <c:if test="${empty logId}">
	            <button id="landBtn" onclick="location.href='<%=request.getContextPath() %>/register/registerJoin'">회원가입하기</button>
	            </c:if>
	    </div>
	    <div id="landingImage" style="display:flex; margin-left:90px;">
	        <div style="position:absolute"><img src="<%= request.getContextPath() %>/img/Ellipse.png" style="width: 115.45px; height: 115.45px;"/>
	        <img src="<%= request.getContextPath() %>/img/Chart.png" style="width: 120.45px; height: 90.98px; margin-left: 40px;"/></div>
	        <div style="position:relative;"><span><img src="<%= request.getContextPath() %>/img/Woman.png" style="width: 130px; height: 195px;"/>
	        <img src="<%= request.getContextPath() %>/img/Chart 1.png"  style="width: 90.45px; height: 60.98px;"/>
	        <img src="<%= request.getContextPath() %>/img/Man.png" style="width: 140px; height: 185px;"/></span></div>
	    </div>
	</div>
	
<!-- 	<div class="search"><input type="text" id="searchLand" placeholder="지금 어떤 업무 도움이 필요하신가요?"/>
	<button id="landBtn2">검색하기</button></div> -->
    <div style="margin-top:10px;">
	    <form action="/ozz/main/searchMission" style="display: flex;" class="input-container">
	    	<input type="text" name="searchWord" id="searchLand" placeholder="지금 어떤 업무 도움이 필요하신가요?">
			<input type="submit" id="landBtn2" value="검색">
		</form>
	</div>
	
	<c:choose>
  	<c:when test="${not empty logId}">
	<div id="landInfo">
		 <div>
		 <c:forEach items="${mymissioningCnt}" var="ing_cnt">
		 	<li style="font-size:2em; margin-bottom: 10px;">${ing_cnt.ingcnt}</li>
		 	<li>미션 수</li>
		 </c:forEach>
		 </div>
		 <div>
		 <c:forEach items="${mymissionendCnt}" var="end_cnt">
		 	<li style="font-size:2em; margin-bottom: 10px;">${end_cnt.endcnt}</li>
		 	<li>수행된 미션 수</li>
		 </c:forEach>
		 </div>
		 <div>
		 	<li style="font-size:2em; margin-bottom: 10px;">256</li>
		 	<li>회원 수</li>
		 </div>
	</div>
	</c:when>
	<c:otherwise>
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
        <li style="font-size:2em; margin-bottom: 10px;">256</li>
        <li>회원 수</li>
      </div>
    </div>
    </c:otherwise>
    </c:choose>
	
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
	
	<c:if test="${not empty logId}">
	<div style="margin-top:90px;">
		<span style="font-size:1.6em;">진행 중인 미션</span>
		<span><a href="/ozz/mypage/myPageDetail" style="font-size:0.8em; color: #869AAF; float:right;">더보기 ></a></span>
	</div>
	<div id="mIng">
	<c:choose>
		<c:when test="${not empty mymissionList}">
		<c:forEach items="${mymissionList}" var="mission_ing">
			<div id="ingList">
				<div class="ingM">
			            <li>${mission_ing.mission_title}</li>
			            <li>진행률<span>${mission_ing.mission_rate}%</span><span>남은 스텝 수</span><span>${mission_ing.restcnt}</span></li>
			            <li><progress id="progress" value="${mission_ing.mission_rate}" min="0" max="100" style="margin: 5px 0 8px 0;"></progress></li>
			            <li><button>이어서 진행하기</button></li>
				</div>
			</div>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="notStart">진행 중인 미션이 없습니다. 미션을 진행해보세요!</div>
		</c:otherwise>
	</c:choose>
		<!-- <div id="ingList">
			<div class="ingM">
				<li>다양한 사례로 익히는 SQL 데이터 분석</li>
				<li>
					<span>진행률 20%</span>
					<span>남은 스텝 수 00개</span>
				</li>
				<li><progress id="progress" value="20" min="0" max="100" style="margin: 5px 0 8px 0;"></progress></li>
				<li><button>이어서 진행하기</button></li>
			</div>
		</div>
		<div id="ingList">
			<div class="ingM">
				<li>클라우드 서비스 AWS</li>
				<li>
					<span>진행률 20%</span>
					<span>남은 스텝 수 00개</span>
				</li>
				<li><progress id="progress" value="20" min="0" max="100" style="margin: 5px 0 8px 0;"></progress></li>
				<li><button>이어서 진행하기</button></li>
			</div>
		</div> -->
	</div>	
		<div style="font-size:1.6em; margin-top: 90px;">맞춤 추천 미션</div>
		<div id="recommendM">
			<div id="recommendMList">
			 	<c:forEach items="${responseBodyList['Title']}" var="title" varStatus="loop">
		            <div class="rMInfo">
		                <li><img src="<%= request.getContextPath()%>/img/Frame 2526 (3).png" /></li>
		                <li>${title.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("'", "").replaceAll("\\{", "").replaceAll("\\}", "")}</li>
		                <c:forEach items="${responseBodyList['Content'][loop.index]}" var="content">
		            		<li>${content.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("'", "")}</li>
		        		</c:forEach>
		                <li>
		                <c:forEach items="${fn:split(responseBodyList['Tag'][loop.index], ',')}" var="tag">
		                    <span class="spanTag">${tag.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("'", "")}</span>
		                </c:forEach>
		                </li>
						<c:forEach items="${responseBodyList['Author'][loop.index]}" var="author">
		            		<li>${author.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("'", "")}</li>
		        		</c:forEach>
		            </div>
		        </c:forEach>
			</div>
		</div>
	</c:if>
	
	<div id="popM">
		<li style="font-size: 1.6em">인기 미션 Top 10</li>
		<li style="color: #869AAF; margin-top: 5px; font-size: 0.9em">지금 가장 많이 도전하고 있는 미션</li>
	</div>
	<div id="popMList">
		<c:forEach var="M_dto" items="${MissionToplist}" varStatus="loop">
			<div class="popMContent">
				<c:choose>
					<c:when test="${loop.index + 1 < 10}">
						<span style="margin-bottom:20px;">
							0<c:out value="${loop.index + 1}" />
						</span>
						<!-- 01 자리에 반복 인덱스를 표시 -->
					</c:when>
					<c:otherwise>
						<div>
							<c:out value="${loop.index + 1}" />
						</div>
					</c:otherwise>
				</c:choose>
				<!-- 01 자리에 반복 인덱스를 표시 -->
				<div>
					<img src="<%=request.getContextPath()%>/img/image 70.jpg" />
				</div>
				<div class="popMInfo">
					<li><a href="/ozz/Mission/MissionView?no=${M_dto.mission_no}">${M_dto.mission_title}</a></li>
					<li><c:forEach items="${fn:split(M_dto.mission_tag, ',')}" var="category"><span class="spanTag">${category}</span></c:forEach></li>
					<li>${M_dto.userid}</li>
				</div>
			</div>
		</c:forEach>
	</div>
	
	
	
	<div id="popMas">
		<div style="font-size:1.6em; margin: 90px 0 35px 0">이 달의 마스터</div>
			<div id="popMa">
				<div id="popMaInfo">
					<span><img src="<%= request.getContextPath()%>/img/Frame 2.png"/></span>
					<span>손승운</span>
				</div>
				<div id="popMaContent">
					<div class="popMaContents">
						<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (1).png"/></li>
						<li>클라우드 서비스 AWS</li>
						<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
					</div>
					<div class="popMaContents">
						<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (2).png"/></li>
						<li>따라하며 배우는 리액트 A-Z</li>
						<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
					</div>
					<div class="popMaContents">
						<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (3).png"/></li>
						<li>ProtoPie Master Courses</li>
						<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
					</div>
				</div>
			</div>
				<div id="popMa">
					<div id="popMaInfo">
						<span><img src="<%= request.getContextPath()%>/img/Frame 2.png"/></span>
						<span>임용진</span>
					</div>
					<div id="popMaContent">
						<div class="popMaContents">
							<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (3).png"/></li>
							<li>클라우드 서비스 AWS</li>
							<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
						</div>
						<div class="popMaContents">
							<li><img src="<%= request.getContextPath()%>/img/Frame 2526.png"/></li>
							<li>따라하며 배우는 리액트 A-Z</li>
							<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
						</div>
						<div class="popMaContents">
							<li><img src="<%= request.getContextPath()%>/img/Frame 2526 (3).png"/></li>
							<li>ProtoPie Master Courses</li>
							<li><span>프로그래밍</span><span>Python</span><span>Java</span></li>
						</div>
					</div>
				</div>
	</div>

</main>