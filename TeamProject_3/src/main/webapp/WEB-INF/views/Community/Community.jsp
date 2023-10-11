<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<main>
	<h1>인기 TOP 5</h1>
	<h3><a href="/ozz/Qna/Qnalist">QnA 바로가기</a></h3>
	<ul class="QnaToplist">
	<li>QnA 번호 / QnA / QnA 작성자 / QnA 등록일 / 조회수</li>
		<c:forEach var="Q_dto" items="${QnaToplist}">
			<li>${Q_dto.qna_no} / <a href="/ozz/Qna/QnaView?no=${Q_dto.qna_no}" style="color: red;">${Q_dto.qna_title}</a> / ${Q_dto.userid}
			${Q_dto.created_at} / ${Q_dto.hit}</li>
		</c:forEach>
	</ul></br>
	
	<h3><a href="/ozz/Board/Boardlist">자유게시판 바로가기</a></h3>
	<ul class="BoardToplist">
	<li>Board 번호 / Board / Board 작성자 / Board 등록일 / 조회수</li>
		<c:forEach var="B_dto" items="${BoardToplist}">
			<li>${B_dto.board_no} / <a href="/ozz/Board/BoardView?no=${B_dto.board_no}" style="color: red;">${B_dto.board_title}</a> / ${B_dto.userid}
			${B_dto.created_at} / ${B_dto.hit}</li>
		</c:forEach>
	</ul>
</main>