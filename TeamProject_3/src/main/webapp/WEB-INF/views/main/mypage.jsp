<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div id="myPage">
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
					<button id="subscribe">구독 내역</button>
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
					<li>진행 중인 미션<span id="more" style="cursor:pointer;">더보기 ></span></li>
					<li>Persona(페르소나) 분석 미션</li>
					<li>진행률<span>20%</span><span>남은 스텝 수</span><span>00</span></li>
					<li><progress id="progress2" value="20" min="0" max="100" style="margin: 5px 0 8px 0;"></progress></li>
					<li><button style="cursor:pointer;">이어서 진행하기</button></li>
				</div>			
			</div>
			<div id="pageRight">
				<ul class="btnList">
					<div class="btn_tap click">내가 만든 미션(3)</div>
					<div class="btn_tap">내가 제출한 미션(1)</div>
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
					<div class="submit">
						<div class="submitM">
						<li>Figma 사용법 미션</li>
						<li>2000-00-00<span>피드백 현황 완료</span></li>
						</div>
						<div><button>리뷰하기</button></div>
					</div>
					<div class="submit">
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
					</div>
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
        	let option = "width = 900px, height = 600px, top = 300px, left = 200px, scrollbars = no,  location=no"
        	let openurl = '/ozz/main/subscribe'
        	window.open(openurl, 'popup', option)
        }

		let missionGoPopup = document.querySelector('span#more');
		
		missionGoPopup.onclick = function() {
			let option2 = "width = 900px, height = 600px, top = 300px, left = 200px, scrollbars = yes,  location=no"
			let openurl2 = '/ozz/main/missionIng'
			window.open(openurl2, 'missionGoPopup', option2)
		}

    </script>