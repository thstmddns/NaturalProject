<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!-- JavaScript 라이브러리를 포함 -->
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<table>
    <tr>
        <th>미션제목</th>
        <th>미션시작일</th>
        <th>진행률</th>
    </tr>
    <c:forEach items="${participatingChallenges}" var="mission">
        <tr>
            <td>${mission.mission_title}</td>
            <td>${mission.chalStartDate}</td>
            <td>${mission.chalEndDate}</td>
            <td>${mission.achievementRate}%</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>