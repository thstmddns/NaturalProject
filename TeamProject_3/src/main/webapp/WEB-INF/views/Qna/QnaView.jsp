<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<style>
	#btn button{
		width: 100%;
		height: 40px;
		text-align:center;
		color: #4E657E;
		border: 1px solid  #DAE2EB;
		background: white; 
		border-radius: 100px;
	} 
	
	#btn button:hover {
		background: #F1F4F8; 
		color: black;
	} 
	#replyList input[value=수정], #replyList input[value=삭제] {
		border: none;
		background:#F1F4F8;
		color: #4E657E;
		cursor: pointer;
	}
	#replyList input[value=수정하기] {
		width: 15%;
		padding: 10px 15px;
		color: #4E657E;
		border: none; 
		background:#F1F4F8;
		border-radius: 100px;
		margin-left: 20px;
		margin-top: 0;
	}
</style>

<script>
	function delChk() {
	    //확인(true) 취소(false) 버튼
	    if (confirm("글을 삭제하시겠습니까?")) {
	       location.href="/ozz/Qna/QnaDel?no=${dto.qna_no}";
	    }
	}
	$(function(){
		//댓글목록 가져오기
		function replyAllList(){
			$.ajax({
				url:'/ozz/reply/replyList',
				data:{
					qna_no:${dto.qna_no} //원글 글 번호
				},
				success:function(replyResult){
					console.log(replyResult);
					$("#replyList").html(""); //원래 있는 목록
					if (replyResult.length === 0) {
		                // 댓글 목록이 비어 있는 경우
		                $("#replyList").hide();
		            } else {
		                // 댓글 목록이 비어 있지 않은 경우
		                $("#replyList").show();
		            }
					$(replyResult).each(function(i, coment){
						var tag = "<div id='replyDetail'><div>";
						tag += "<b>"+coment.userid+"<span>"+coment.created_at+"</span></b>";
						//수정, 삭제버튼
						//로그인 한 사람이 글을 쓴 댓글일 때
						if(coment.userid=='${logId}'){//'dong' == 'asd'
							tag += "<input type='button' value='수정' style='margin-left: 20px;'/>";
							tag += "<input type='button' value='삭제' title='"+coment.qnr_no+"'/>";
							tag += "<p>"+coment.coment+"</p></div>"; //댓글내용
							//수정 폼
							tag += "<div style='display:none'>";
							tag += "<form>";
							tag += "<textarea name='coment' id='coment' placeholder='&nbsp;&nbsp;댓글을 입력하세요.'>";
							//글내용수정, 댓글번호
							tag += coment.coment;
							tag += "</textarea>";
							tag += "<input type='hidden' name='qnr_no' value='"+coment.qnr_no+"'/>";
							tag += "<input type='button' value='수정하기'/>";
							tag += "</form>";
							tag += "</div>";
						}else{
							tag += "<div id='replyContent'>"+coment.coment+"</div>";
						}
						tag += "</div>";
						
						$("#replyList").append(tag);
					});
				},
				error:function(e){
					console.log(e.responseText);
				}
			});
		}
		//댓글쓰기
		$("#replyFrm").submit(function(){
			//form 태그의 기본 이동의 기능을 가진 action으로 이동하는 것을 해제
			event.preventDefault();
			//0. 댓글 입력 여부 확인
			if($("#coment").val()==""){
				alert("댓글을 입력하세요..");
				return false;
			}
			//1. 데이터 준비 no=99&coment=댓글내용 -> 폼 내의 값을 쿼리로 만들어주는 함수(serialize())
			var params = $("#replyFrm").serialize();
			console.log('params', params);
			
			//2. ajax실행
			$.ajax({
				url:'/ozz/reply/replyWrite',
				type:'POST',
				data:params,
				success:function(result){
					console.log(result);
					//이미 DB에 등록된 글 폼에서 지우기
					$("#coment").val("");
					//댓글리스트 다시 출력한다.
					replyAllList();
				},
				error:function(e){
					console.log(e.responseText);
				}
			});
		});
		//댓글수정폼
		$(document).on('click','#replyList input[value=수정]',function(){
			//해당 댓글은 숨기고
			$(this).parent().css('display','none');
			//수정폼은 보여주기
			$(this).parent().next().css('display','block');
		});
		//댓글수정(DB)
		$(document).on('click','#replyList input[value=수정하기]',function(){
			var params = $(this).parent().serialize(); //re_no=88&coment=asdasd
			
			$.ajax({
				url : '/ozz/reply/replyEditOk',
				data : params,
				type : 'POST',
				success : function(result){
					console.log(result)
					if(result=='0'){
						alert('댓글이 수정되지않았습니다.');
					}else{
						replyAllList();
					}
				},error:function(){
					console.log("댓글수정실패");
				}
			});
		});
		//댓글삭제
		$(document).on('click','#replyList input[value=삭제]',function(){
			if(!confirm('댓글을 삭제하시겠습니까?')){
				return false;
			}
			//댓글번호 attr(), prop()
			//		attr('title'), attr('title','200')
			var qnr_no = $(this).attr('title')
			$.ajax({
				url:"/ozz/reply/replyDel",
				data : {
					qnr_no:qnr_no
				},
				success:function(result){
					if(result=='0'){
						alert('댓글이 삭제되지 않았습니다.');
					}else{
						replyAllList();
					}
				},error:function(e){
					console.log("댓글삭제 에러 발생.");
				}
			});
		});
		
		//해당글의 댓글 목록
		replyAllList();
	});
</script>
<main>
	<input type="hidden" value="${dto.qna_no}">
	<div id="commuView">
		<div>
			<div class="comViewMa">
					<div><img src="<%= request.getContextPath()%>/img/Frame 2.png"/></div>
					<div class="comViewMaInfo">
						<ul>
							<li>${dto.userid}<span></span><span>가전제품 상품기획 DCX Analyst</span></li>
							<li><span>팔로우 100</span><span>팔로잉 100</span></li>
						</ul>
					</div>
					<div><button>팔로우 하기</button></div>
			</div>
		
		
			<div id="commuInfo">
				<div style="font-size:1.5em; margin-bottom: 10px;">[QnA] ${dto.qna_title}</div>
				<div id="commuContent">${dto.qna_content}
					<c:if test="${not empty dto.file_name}">
				        <div id="download">
				            <img src="<%= request.getContextPath()%>/img/Download.png" />
				            <p>${dto.file_name}</p>
				        </div>
				    </c:if>
				</div>
			</div>
		
			<div style="font-size:1.3em; margin: 60px 0 30px 0;">댓글</div>
			<div id="reply">
					<c:if test="${logStatus == 'Y'}">
						<form method="post" id="replyFrm">
							<!-- 원글 번호 -->
							<input type="hidden" name="qna_no" value="${dto.qna_no }">  
							<textarea name="coment" id="coment" placeholder="&nbsp;&nbsp;댓글을 입력하세요."></textarea>
							<input type="submit" value="댓글 등록하기">
						</form>
					</c:if>
				<ul id="replyList">
				</ul>
			</div> 
		</div>
		
		<div style="width:100%;">
			<div id="btn">
				<p><button>마음에 들어요&nbsp;<img src="<%= request.getContextPath()%>/img/Component 2.png"/></button></p>
				<c:if test="${logId == dto.userid}">
					<p><button onclick="location.href='<%=request.getContextPath() %>/Qna/QnaEdit?qna_no=${dto.qna_no}'">게시글 수정하기</button></p>
					<p><button onclick="delChk()">게시글 삭제하기</button></p>
				</c:if>
			</div>
			<div id="recommend">
				<div style="margin-bottom:10px;">추천 게시글</div>
				<div id="recommendContent">
					<li>Figma 활용법</li>
					<li><span>스킬</span><span>태그</span></li>
					<li>홍길동</li>
				</div>
				<div id="recommendContent">
					<li>디자인 시스템 이해</li>
					<li><span>스킬</span><span>태그</span></li>
					<li>홍길동</li>
				</div>
				<div id="recommendContent">
					<li>자바스크립트 이해 및 활용</li>
					<li><span>스킬</span><span>태그</span></li>
					<li>홍길동</li>
				</div>
		
			</div>
		</div>
	</div>
</main>