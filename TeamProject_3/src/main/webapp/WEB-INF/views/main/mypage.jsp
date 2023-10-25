<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div id="myPage">
		<div id="myInfo">
			<div id="myInfoDetail">
				<div><img src="<%= request.getContextPath()%>/img/Group 7210.png"/></div>
				<div class="myInfoDetail2">
					<li>${dto.username}<span>가전제품 상품기획 DCX Analyst</span></li>
					<li>팔로워 100<span>팔로잉 100</span></li>
					<li>
						<c:choose>
	                		<c:when test="${empty dto.intro}">
	                   			 자기소개를 입력해주세요.
	                		</c:when>
	                		<c:otherwise>
	                    		${dto.intro}
	                		</c:otherwise>
	            		</c:choose>
            		</li>
				</div>
			</div>
			<div class="career">
				<li>랩포디엑스(주)<span>가전제품 상품기획 DCX Analyst</span><span>2022.11.15 ~ 현재까지</span></li>
				<li>연어보다 맛있는 자연어(주)<span>백엔드 개발자</span><span>2019.08.01 ~ 2022.10.31</span></li>
				<li>스마트인재개발원<span>백엔드 개발자</span><span>2018.02.21 ~ 2019.06.30</span></li>
				<li style="float:right; font-size:13px !important;">더보기 ></li>		
			</div>	
		</div>
		<div id="myInfoBottom">
			<div id="pageLeft">
				<div id="pageButton">
					<button id="subscribe">구독 내역</button>
					<button onclick="location.href='<%=request.getContextPath() %>/mypage/myInfoEdit'">프로필 수정하기</button>
				</div>
				<div id="skill">
					<li># 직무 스킬</li>
					<li><span>경영기획</span><span>개발</span><span>프로젝트관리</span></li>
					<li># 업무 스킬</li>
					<li><span>분석적 사고</span><span>비판적 사고</span><span>프로그래밍</span></li>
					<li># 툴 스킬</li>
					<li><span>python</span><span>java</span><span>javascript</span><span>html/css</span></li>
				</div>
				<div id="missionTotal">
					<li>미션 수행 통계</li>
					<li>완료한 미션<span>1</span></li>
					<li>진행 중인 미션<span>3</span></li>
				</div>
				<div id="missionGo">
					<li>진행 중인 미션<span id="more" style="cursor:pointer;">더보기 ></span></li>
				<c:choose>
				<c:when test="${not empty mymissionList}">
				    <li>${mymissionList[0].mission_title}</li>
				    <li>진행률<span>${mymissionList[0].mission_rate}%</span><span>남은 스텝 수</span><span>${mission_rateionList[0].restcnt}</span></li>
				    <li><progress id="progress2" value="${mymissionList[0].mission_rate}" min="0" max="100" style="margin: 5px 0 8px 0;"></progress></li>
				    <li><button style="cursor:pointer;">이어서 진행하기</button></li>
				</c:when>
				<c:otherwise>
					<div style="color:#4E657E; font-size:0.9em;">진행 중인 미션이 없습니다. 미션을 진행해보세요!</div>
				</c:otherwise>
				</c:choose>
	
				</div>			
			</div>
			<div id="pageRight">
				<ul class="btnList">
					<div class="btn_tap click">내가 만든 미션(3)</div>
					<div class="btn_tap">내가 완료한 미션(1)</div>
					<div class="btn_tap">내가 작성한 커뮤니티(3)</div>
				</ul>
				<div class="pageContent show">
					<div class="made">
						<li>Persona(페르소나) 분석 미션</li>
						<li>리뷰<span>10</span>/<span>전체 질문</span><span>10</span><span>미답변 질문</span><span class="red">3</span>/<span>전체 피드백</span><span>10</span><span>피드백 미완료</span><span class="red">5</span></li>
					</div>
					<div class="made">
						<li>고객 경험 리디자인</li>
						<li>리뷰<span>10</span>/<span>전체 질문</span><span>10</span><span>미답변 질문</span><span class="red">3</span>/<span>전체 피드백</span><span>10</span><span>피드백 미완료</span><span class="red">5</span></li>
					</div>
					<div class="made">
						<li>고객 난제 정의</li>
						<li>리뷰<span>10</span>/<span>전체 질문</span><span>10</span><span>미답변 질문</span><span class="red">3</span>/<span>전체 피드백</span><span>10</span><span>피드백 미완료</span><span class="red">5</span></li>
					</div>
					<div class="made">
						<li>Orange 사용법</li>
						<li>리뷰<span>10</span>/<span>전체 질문</span><span>10</span><span>미답변 질문</span><span class="red">3</span>/<span>전체 피드백</span><span>10</span><span>피드백 미완료</span><span class="red">5</span></li>
					</div>
				</div>
				<div class="pageContent">
					<c:forEach items="${myendmissionList}" var="mission_end">
					<div class="submit">
						<div class="submitM">
						<li>${mission_end.mission_title}</li>
						<li>${mission_end.completed_at}<span>피드백 현황 완료</span></li>
						</div>
						<div><button>리뷰하기</button></div>
					</div>
					</c:forEach>
					<!-- <div class="submit">
						<div class="submitM">
						<li>Orange 사용법</li>
						<li>2000-00-00<span>피드백 현황 완료</span></li>
						</div>
						<div><button>리뷰 완료</button></div>
					</div>
					<div class="submit">
						<div class="submitM">
						<li>React 활용법</li>
						<li>2000-00-00<span>피드백 현황 완료</span></li>
						</div>
						<div><button>리뷰하기</button></div>
					</div> -->
				</div>
				<div class="pageContent">
					<div class="write">
						<li>[질문] 리액트 파일 업로드 관련</li>	
						<div class="writeContent">
							최근에 본 강의 영상을 토대로 저만의 어플리케이션을 만들고 있습니다. 강의 영상에서는 API / Service / Database 로 layer를 분리해서 코드를 짰던 것을 보고 저도 나름 적용해보려고 하는데요. 코드를 짜면 짤...
						</div>
						<li><span>태그</span><span>태그</span><span>태그</span></li>	
						<li>홍길동<span>2000-00-00</span></li>		
					</div>
					<div class="write">
						<li>[질문] 리액트 파일 업로드 관련</li>	
						<div class="writeContent">
							최근에 본 강의 영상을 토대로 저만의 어플리케이션을 만들고 있습니다. 강의 영상에서는 API / Service / Database 로 layer를 분리해서 코드를 짰던 것을 보고 저도 나름 적용해보려고 하는데요. 코드를 짜면 짤...
							최근에 본 강의 영상을 토대로 저만의 어플리케이션을 만들고 있습니다. 강의 영상에서는 API / Service / Database 로 layer를 분리해서 코드를 짰던 것을 보고 저도 나름 적용해보려고 하는데요. 코드를 짜면 짤...
							최근에 본 강의 영상을 토대로 저만의 어플리케이션을 만들고 있습니다. 강의 영상에서는 API / Service / Database 로 layer를 분리해서 코드를 짰던 것을 보고 저도 나름 적용해보려고 하는데요. 코드를 짜면 짤...
							최근에 본 강의 영상을 토대로 저만의 어플리케이션을 만들고 있습니다. 강의 영상에서는 API / Service / Database 로 layer를 분리해서 코드를 짰던 것을 보고 저도 나름 적용해보려고 하는데요. 코드를 짜면 짤...
						</div>
						<li><span>태그</span><span>태그</span><span>태그</span></li>	
						<li>홍길동<span>2000-00-00</span></li>		
					</div>
					<div class="write">
						<li>[질문] 리액트 파일 업로드 관련</li>	
						<div class="writeContent">
							최근에 본 강의 영상을 토대로 저만의 어플리케이션을 만들고 있습니다. 강의 영상에서는 API / Service / Database 로 layer를 분리해서 코드를 짰던 것을 보고 저도 나름 적용해보려고 하는데요. 코드를 짜면 짤...
						</div>
						<li><span>태그</span><span>태그</span><span>태그</span></li>	
						<li>홍길동<span>2000-00-00</span></li>		
					</div>
				</div>
			</div>
		</div>


	</div>

</main>
<script>
        let btn1 = document.querySelectorAll('.btn_tap')[0];
        let btn2 = document.querySelectorAll('.btn_tap')[1];
        let btn3= document.querySelectorAll('.btn_tap')[2];

        const content1 = document.querySelectorAll('.pageContent')[0];
        const content2 = document.querySelectorAll('.pageContent')[1];
        const content3 = document.querySelectorAll('.pageContent')[2];

        btn1.addEventListener('click', () => {
            btn1.classList.add('click');
            btn2.classList.remove('click');
            btn3.classList.remove('click');
            content1.classList.add('show');
            content2.classList.remove('show');
            content3.classList.remove('show');
        })

        btn2.addEventListener('click', () => {
            btn2.classList.add('click');
            btn1.classList.remove('click');
            btn3.classList.remove('click');
            content2.classList.add('show');
            content1.classList.remove('show');
            content3.classList.remove('show');
        })

        btn3.addEventListener('click', () => {
            btn1.classList.remove('click');
            btn2.classList.remove('click');
            btn3.classList.add('click');
            content1.classList.remove('show');
            content2.classList.remove('show');
            content3.classList.add('show');

        })
        
        let popupbtn = document.querySelector('button#subscribe');
        
        popupbtn.onclick = function() {
        	let option = "width = 700px, height = 600px, top = 300px, left = 200px, scrollbars = no,  location=no"
        	let openurl = '/ozz/mypage/subscription_list'
        	window.open(openurl, 'popup', option)
        }

		let missionGoPopup = document.querySelector('span#more');
		
		missionGoPopup.onclick = function() {
			let option2 = "width = 900px, height = 600px, top = 300px, left = 200px, scrollbars = yes,  location=no"
			let openurl2 = '/ozz/mypage/mission_ing'
			window.open(openurl2, 'missionGoPopup', option2)
		}

    </script>