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

    <h1>진행 중인 미션 목록</h1>

    <h2>미션 목록</h2>
    <table>
        <tr>
            <th>미션번호</th>
            <th>달성률</th>
        </tr>
        <c:forEach items="${mymissionList}" var="mission_ing">
            <tr>
                <td>${mission_ing.mission_no}</td>
                <td>${mission_ing.mission_rate}%</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>