<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h1>완료한 미션 목록</h1>

    <h2>미션 목록</h2>
    <table>
        <tr>
            <th>미션제목</th>
            <th>미션제출일</th>
            <th>피드백점수</th>
        </tr>
        <c:forEach items="${myendmissionList}" var="mission_end">
            <tr>
                <td>${mission_end.mission_title}</td>
                <td>${mission_end.completed_at}</td>
                <td>${mission_end.feedback_score}점</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>