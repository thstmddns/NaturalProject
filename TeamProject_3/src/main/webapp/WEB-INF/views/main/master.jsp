<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
	<div id="landingM">
	    <div style="flex-direction: column; justify-content: flex-start; align-items: flex-start; gap: 20px; display: inline-flex;">
	        <div style="margin-top: 60px;"><span style="color: #222A2F; font-size: 30px; word-wrap: break-word;">오즈의 마스터들과 이야기를 나누어보세요.</span></div>
	            <button id="landBtn" onclick="location.href='<%=request.getContextPath() %>/Board/Boardwrite'">커뮤니티에 글쓰기</button>
	    </div>
	    <div id="landingImage" style="display:flex; margin-left:70px; width: 580px; height: 280px;">
	        <img src="<%= request.getContextPath()%>/img/8.png"/>
	    </div>
	</div>
	
	<div id="mainMaPop">
			<li style="font-size:1.6em">주간 인기 글 마스터</li>
			<li style="color: #869AAF; margin-top:5px; font-size:0.9em">지금 가장 많은 분들이 읽으시는 글의 마스터!</li>
	</div>
	<div id="mainMaPopL">
			<div class="mainMaPopInfo">
				<div>01</div>
				<div><img src="<%= request.getContextPath()%>/img/Group 7210.png"/></div>
				<div class="mainMaInfo">
				<li><span>홍길동</span><span class="maMInfo" style="margin-left:20px;">가전제품 상품기획 DCX Analyst</span></li>
				<li style="margin-top:5px;">
					<span class="maMInfo">팔로워 100</span>
					<span class="maMInfo">팔로잉 100</span>
					<span class="maMInfo">만든 미션 수 50</span>
				</li>
				</div>
			</div>
			<div class="mainMaPopInfo">
				<div>02</div>
				<div><img src="<%= request.getContextPath()%>/img/Group 7210.png"/></div>
				<div class="mainMaInfo">
				<li><span>세종대왕</span><span class="maMInfo" style="margin-left:20px;">가전제품 상품기획 DCX Analyst</span></li>
				<li style="margin-top:5px;">
					<span class="maMInfo">팔로워 100</span>
					<span class="maMInfo">팔로잉 100</span>
					<span class="maMInfo">만든 미션 수 50</span>
				</li>
				</div>
			</div>
			<div class="mainMaPopInfo">
				<div>03</div>
				<div><img src="<%= request.getContextPath()%>/img/Group 7210.png"/></div>
				<div class="mainMaInfo">
				<li><span>궁예</span><span class="maMInfo" style="margin-left:20px;">가전제품 상품기획 DCX Analyst</span></li>
				<li style="margin-top:5px;">
					<span class="maMInfo">팔로워 100</span>
					<span class="maMInfo">팔로잉 100</span>
					<span class="maMInfo">만든 미션 수 50</span>
				</li>
				</div>
			</div>
			<div class="mainMaPopInfo">
				<div>04</div>
				<div><img src="<%= request.getContextPath()%>/img/Group 7210.png"/></div>
				<div class="mainMaInfo">
				<li><span>왕건</span><span class="maMInfo" style="margin-left:20px;">가전제품 상품기획 DCX Analyst</span></li>
				<li style="margin-top:5px;">
					<span class="maMInfo">팔로워 100</span>
					<span class="maMInfo">팔로잉 100</span>
					<span class="maMInfo">만든 미션 수 50</span>
				</li>
				</div>
			</div>
		</div>
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