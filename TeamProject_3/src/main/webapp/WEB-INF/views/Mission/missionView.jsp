<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<main>
	<input type="hidden" value="${dto.mission_no}">
   <div id="missionView">
      <div id="missionLeft">
         <div id="missionInfo">
            <li>${dto.mission_title}</li>
            <li><c:forEach items="${fn:split(dto.mission_tag, ',')}" var="category">
                <span>${category}</span></c:forEach></li>
            <div> ${dto.mission_content}</div>
            <li>소요시간<span>8시간</span><span>카테고리</span><c:forEach items="${fn:split(dto.mission_cate, ',')}" var="category2">
                <span>${category2}</span></c:forEach></li>
         </div>
         
         <div id="missionMaster">
            <li>이 미션의 랜선 <span style="color:#F93D18;">사수</span></li>
            <li style="font-size:1.2em;">${dto.userid}<span>가전제품 상품기획 DCX Analyst</span></li>
            <li>팔로워<span>100</span><span>팔로잉</span><span>100</span></li>
         </div>
         
         <div id="learnSkill">
            <li>미션을 통해 습득할 <span style="color:#F93D18;">스킬</span></li>
            <li>이 미션을 수행하면 이런 스킬을 획득할 수 있어요!</li>
            <li><c:forEach items="${fn:split(dto.mission_skill, ',')}" var="category3">
                <span>${category3}</span></c:forEach></li>
         </div>
         
         <div id="require">
            <p style="font-size:1.2em;">미션을 시작하기 위한 <span style="color:#F93D18;">재료</span></p>
            <div class="ingredients">
               <div class="ingredient">
                  <li>데이터<span>이런 파일 확장자를 사용합니다.</span></li>
                  <div><c:forEach items="${fn:split(dto.mission_data, ',')}" var="category4">
                <span>${category4}</span></c:forEach></div>
               </div>
               <div class="ingredient">
                  <li>Tool<span>이런 툴을 사용합니다.</span> </li>
                  <div><c:forEach items="${fn:split(dto.mission_tool, ',')}" var="category5">
                <span>${category5}</span></c:forEach></div>
               </div>
            </div>
         </div>
         
         <div id="missionPractice">      
         <div style="margin: 80px 0 20px 0; font-size: 1.5em;">미션 실행하기</div>
            <div class="missionStep">
            <c:forEach var="StepDTO" items="${Steplist}">
	               <div class="missionStepDetail">
	                  <div>
	                  <li style="font-size:1.2em;"><span>step ${StepDTO.step}</span><a href="/ozz/Step/StepView?no=${StepDTO.step_no}&mission_no=${dto.mission_no}">${StepDTO.step_title}</a></li>
	                  <li>미션 스텝 제출 여부<span>제출 완료</span><span>미션 스텝 제출 일시</span><span>2000.00.00</span></li>
	                  </div>
	                  <div><button class="complete">완료  <img src="<%= request.getContextPath()%>/img/체크.png"/></button></div>
	               </div>
            </c:forEach>
               <div class="missionStepDetail">
                  <div>
                  <li style="font-size:1.2em;"><span>step 3</span>주피터 노트북 세팅</li>
                  <li>미션 스텝 제출 여부<span>제출 안 함</span><span>미션 스텝 제출 일시</span><span>-</span></li>
                  </div>
                  <div><button class="ing">진행 중</button></div>
               </div>
               <div class="missionStepDetail">
                  <div>
                  <li style="font-size:1.2em;"><span>step 4</span>분석할 코드 만들기</li>
                  <li>미션 스텝 제출 여부<span>제출 안 함</span><span>미션 스텝 제출 일시</span><span>-</span></li>
                  </div>
                  <div><button class="start">시작하기 ></button></div>
               </div>
               <div class="missionStepDetail">
                  <div>
                  <li style="font-size:1.2em;"><span>step 5</span>파라메터 조정</li>
                  <li>미션 스텝 제출 여부<span>제출 안 함</span><span>미션 스텝 제출 일시</span><span>-</span></li>
                  </div>
                  <div><button class="start">시작하기 ></button></div>
               </div>
               <div class="missionStepDetail">
                  <div>
                  <li style="font-size:1.2em;"><span>step 6</span>코드 실행 : 클러스터링</li>
                  <li>미션 스텝 제출 여부<span>제출 안 함</span><span>미션 스텝 제출 일시</span><span>-</span></li>
                  </div>
                  <div><button class="start">시작하기 ></button></div>
               </div>
               <div class="missionStepDetail">
                  <div>
                  <li style="font-size:1.2em;"><span>step 7</span>최종 산출물 제출</li>
                  <li>미션 스텝 제출 여부<span>제출 안 함</span><span>미션 스텝 제출 일시</span><span>-</span></li>
                  </div>
                  <div><button class="start">시작하기 ></button></div>
               </div>
               
            </div>
         </div>
         
         <div id="lastPang">
            <p style="margin: 80px 0 20px 0; font-size: 1.5em;">최종 산출물</p>
            <div>Persona(페르소나) 분석 결과물</div>
         </div>
         
         
         <div id="missionReview" style="width:90%;">
            <div style="margin-top: 80px; font-size: 1.5em;">리뷰<span style="font-size: 0.6em; color:#869AAF; float:right;">더보기 ></span></div>
            <c:choose>
		        <c:when test="${not empty M_Reviewlist}">
		            <c:forEach var="M_Reviewlist" items="${M_Reviewlist}">
		                <div class="missionReviewExam">
		                    <li>${M_Reviewlist.userid}<span>${M_Reviewlist.created_at}</span><span>평점: ${M_Reviewlist.review_score}</span></li>
		                    <div class="reviewTitle">${M_Reviewlist.review_title}</div>
		                    <div class="reviewText">${M_Reviewlist.review_content}</div>
		                </div>
		            </c:forEach>
		        </c:when>
		        <c:otherwise>
		            <div class="missionReviewExam" style="color: #4E657E;">리뷰가 없습니다. 미션을 완료하고 리뷰를 작성해보세요!</div>
		        </c:otherwise>
    		</c:choose>
         </div>
         
       
         
         <div id="missionQnA">
            <div style="margin-top: 80px; font-size: 1.5em;">Q&A<span style="font-size: 0.6em; color:#869AAF; float:right;">더보기 ></span></div>
            <div id="QnAContent">
               <button onclick="location.href='<%=request.getContextPath() %>/Qna/QnaWrite?no=${dto.mission_no}&mission_title=${dto.mission_title}'">질문이 있으신가요? <span style="text-decoration:underline;">Q&A 작성하기</span></button>
               <c:choose>
			       <c:when test="${not empty M_Qnalist}">
	               <c:forEach var="M_Qnalist" items="${M_Qnalist}">
	               <div class="QnADetail">
	                  <li>[질문] ${M_Qnalist.qna_title}</li>
	                  <li><span>Q&A</span></li>
	                  <li>${M_Qnalist.userid}<span class="QnACreate">${M_Qnalist.created_at.split(' ')[0] }</span></li>
	                  <c:if  test="${logStatus=='Y'}">
	                  <input type="textarea" placeholder="  ↳  답변 달기">
	                  </c:if>
	               </div>
	               </c:forEach>
	               </c:when>
			        <c:otherwise>
			            <div class="QnADetail" style="color: #4E657E;">QnA가 없습니다. 미션에 대한 질문을 작성해보세요!</div>
			        </c:otherwise>
    			</c:choose>
            </div>
         </div>
         
         <%-- <c:if  test="${}"> --%>
         <div>   
            <p style="margin-top: 80px; font-size: 1.5em;">피드백</p>
            <div id="feedback">
               <div>이상의 이상을 얼음에 뿐이다. 실현에 때까지 가치를 이것은 황금시대의 목숨이 사람은 때에, 동력은 힘있다. 인생의 소담스러운 갑 피부가 있다. 커다란 산야에 인간이 같지 것이다. 같은 살았으며, 피는 풍부하게 위하여서. 행복스럽고 능히 몸이 것이다. 긴지라 노래하며 위하여, 피어나는 원대하고, 이것이다. 얼마나 이상 이것을 그것은 심장은 이상의 있음으로써 속잎나고, 사막이다. (더미 텍스트)</div>
               <div class="score">점수 <span>5</span> / 10 점</div>
            </div>
         
         
         </div>
      <%--    </c:if> --%>
      
      <%-- <c:if  test="${}"> --%>
         <form id="reviewWriteForm">
            <div id="reviewWrite">
               <input type="hidden" name="mission_no" value="${dto.mission_no }">
               <p>${dto.mission_title}</p>
               <p>피드백까지 미션 스텝이 모두 완료되었습니다!</p>
               <p>평점을 입력해주세요!</p>
               <li><select class="select" title="평점" name="review_score">
                   <option value="">평점</option>
                   <option  id="review_score" value="5">★★★★★</option>
                   <option  id="review_score" value="4">★★★★</option>
                   <option  id="review_score" value="3">★★★</option>
                   <option  id="review_score" value="2">★★</option>
                   <option  id="review_score" value="1">★</option>
               </select></li>
               <div>미션은 어떠셨나요? 
               </br>좋았던 점들이나 개선해야할 점들을 알려주면 마스터에게 도움이 될거에요.</div>
               <input type="text" name="review_title" id="review_title" placeholder="제목을 입력해주세요." style="width: 97%; border-radius: 10px; border: 1px solid #DAE2EB; height: 30px; margin: 0 0 15px 10px;">   
               <input type="textarea" name="review_content" id="review_content">
               <button type="submit" form="reviewWriteForm">미션 리뷰 등록하기</button>
            </div>
            </form>
      <%--    </c:if> --%>
      </div>
   
      <div id="missionRight">
         <div id="date">
            <li>등록일<span>${dto.created_at.split(' ')[0] }</span></li>
            <li>시작일<span>22.00.00</span></li>
            <li>완료일<span>-</span></li>
            <li>진행률<span>20%</span></li>
         </div>
         <%-- <c:if test=""> --%>
            <div id="stepSubmit2">
            <li>미션 스텝 제출 여부</li>
            <li>제출 안 함</li>
            <li>미션 스텝  제출 일시</li>
            <li>-</li>
            <li>피드백 현황<span>-</span></li>
         </div>
         
         <div>
         <p>${dto.mission_title}</p>
         <div id="totalStep2">
         <c:forEach var="StepDTO" items="${Steplist}">
            <li>${StepDTO.step}.  ${StepDTO.step_title}</li>
         </c:forEach>
         </div>
         
   <%--       </c:if> --%>
         
         
         <div id="missionBtn">
            <button>미션 추천하기 <img src="<%= request.getContextPath()%>/img/Thumbs-up.png"/></button>
            <c:if test="${logId == dto.userid}">
            <button>미션 수정하기</button>
            <button onclick="delChk()">미션 삭제하기</button>
            </c:if>     
         </div>
         
         <div id="recommend">
         <div style="margin-bottom:10px;">추천 미션</div>
         <div id="recommendContent">
            <li>Figma 활용법</li>
            <li><span>웹</span><span>jsp</span></li>
            <li>홍길동</li>
         </div>
         <div id="recommendContent">
            <li>디자인 시스템 이해</li>
            <li><span>디자인</span><span>웹</span></li>
            <li>홍길동</li>
         </div>
         <div id="recommendContent">
            <li>자바스크립트 이해 및 활용</li>
            <li><span>javaScript</span><span>java</span></li>
            <li>홍길동</li>
         </div>
      
      </div>
      </div>
      
      
   </div>
   




</main>


<script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/super-build/ckeditor.js"></script>
<script>
	

    $("#reviewWriteForm").submit(function() {
      // form 태그의 기본 이동 기능을 가진 action으로 이동하는 것을 해제
      // button으로 만들면 action 없음
      event.preventDefault();  
      
      // 1. 댓글 입력 확인
	if($("#review_title").val() == "") {
         alert("리뷰 제목을 입력하세요");
         return false;
      }
      if($("#review_content").val() == "") {
         alert("리뷰 내용을 입력하세요");
         return false;
      }
      // 2. 데이터 준비   no=99&coment=댓글내용 ->  폼 내의 값을 쿼리로 만들어주는 함수(serialize())
      var params = $("#reviewWriteForm").serialize();
      console.log('params', params);
      
      // 3. ajax 실행
      $.ajax({
         url: '/ozz/Review/ReviewwriteOk',
         type: 'POST',
         data: params,
         success:function(result) {
            console.log(result);
            // 이미 DB에 등록된 글 폼에서 지우기
 	    $("#reviewWriteForm").css('display', 'None');
            
         },
         error:function(e){
            console.log(e.responseText);
         } 
      });
         
   }); 

   // 리뷰 제출 버튼을 클릭할 때 이벤트 핸들러를 실행
  /*  $("#reviewWriteForm").submit(function(event) {
       // 폼 제출의 기본 동작을 중지
       event.preventDefault();
       
       // 리뷰 정보를 FormData 객체로 수집
       var formData = new FormData(this);
       
       // Ajax 요청을 생성
       $.ajax({
           url: "{pageContext.request.contextPath}/Review/ReviewwriteOk", // 서버의 컨트롤러 주소로 변경
           type: "POST",
           data: formData,
           contentType: false,
           processData: false,
           success: function (response) {
        	   console.log(response);
               // 서버로부터 받은 응답 처리 (예: 리뷰 등록 성공/실패 처리)
               if (response === "success") {
                   alert("리뷰가 성공적으로 등록되었습니다.");
                   // 리뷰 등록 후 어떤 동작을 하고자 하는 경우에 여기에 추가
               } else {
                   alert("리뷰 등록에 실패했습니다.");
                   // 실패 시 어떤 동작을 하고자 하는 경우에 여기에 추가
               }
           },
           error: function (error) {
               // 오류 처리 (예: 서버 오류)
               console.error("리뷰 등록 중 오류 발생: " + error);
           }
       });
   });
 */


</script> 