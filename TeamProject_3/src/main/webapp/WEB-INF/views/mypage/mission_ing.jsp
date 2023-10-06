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
    
    <table>
        <tr>
            <th>미션 번호</th>
            <th>미션 제목</th>

        </tr>
        <c:forEach items="${list}" var="dto">
            <tr>
                <td>${dto.mission_no}</td>
                <td>${dto.mission_title}</td>

            </tr>
        </c:forEach>
    </table>


</body>
</html>