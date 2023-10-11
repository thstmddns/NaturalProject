<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
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
					$(replyResult).each(function(i, coment){
						var tag = "<li><div>";
						tag += "<b>"+coment.userid+"("+coment.created_at+")</b>";
						//수정, 삭제버튼
						//로그인 한 사람이 글을 쓴 댓글일 때
						if(coment.userid=='${logId}'){//'dong' == 'asd'
							tag += "<input type='button' value='Edit'/>";
							tag += "<input type='button' value='Del' title='"+coment.qnr_no+"'/>";
							tag += "<p>"+coment.coment+"</p></div>"; //댓글내용
							//수정 폼
							tag += "<div style='display:none'>";
							tag += "<form>";
							tag += "<textarea style='width:400px' name='coment'>";
							//글내용수정, 댓글번호
							tag += coment.coment;
							tag += "</textarea>";
							tag += "<input type='hidden' name='qnr_no' value='"+coment.qnr_no+"'/>";
							tag += "<input type='button' value='수정하기'/>";
							tag += "</form>";
							tag += "</div>";
						}else{
							tag += "<p>"+coment.coment+"</p></div>";
						}
						tag += "</li>";
						
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
		$(document).on('click','#replyList input[value=Edit]',function(){
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
		$(document).on('click','#replyList input[value=Del]',function(){
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
	<h1>글내용보기</h1>
	<div>
		<a href='/ozz/Qna/Qnalist?nowPage=${pDTO.nowPage}<c:if test="${pDTO.searchWord != null}">&searchKey=${pDTO.searchKey}&searchWord=${pDTO.searchWord}</c:if>'>목록</a>
	</div>
	<ul>
		<li>글번호 : ${dto.qna_no}</li>
		<li>글쓴이 : ${dto.userid}</li>
		<li>등록일 : ${dto.created_at}</li>
		<li>제목 : ${dto.qna_title}</li>
		<li>글내용<br/>
			${dto.qna_content}</li>
		
		<li>${dto.file_name}</li>
	</ul>
	<div>
		<!-- session의 로그인 아이디(logId)와 현재 글쓴이(userid)가 같으면 수정,삭제 표시한다. -->
		<c:if test="${logId == dto.userid}">
			<a href="/ozz/Qna/QnaEdit?no=${dto.qna_no}">수정</a>
			<a href="javascript:delChk()">삭제</a>
		</c:if>
	</div>
	
	<!-- 댓글달기 -->
	<style>
		#coment{width:500px; height:80px;}
		#replyList>li{border-bottom:1px solid #ddd; padding:5px 0px}
	</style>
	<div id="reply">
		<!-- 로그인시 댓글 폼 -->
		<c:if test="${logStatus == 'Y'}">
			<form method="POST" id="replyFrm">
				<input type="hidden" name="qna_no" value="${dto.qna_no}"/><!-- 원글번호 -->
				<textarea name="coment" id="coment"></textarea>
				<input type="submit" value="댓글등록하기"/>
			</form>
		</c:if>
		<hr/>
		<ul id="replyList">
		</ul>
	</div>
</main>