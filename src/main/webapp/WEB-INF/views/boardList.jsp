<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/list.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
</head>
<body>
<div style="text-align:center">
    <div class="board-container">
        <table>
            <tr>
                <th class="no">번호</th>
                <th class="title">제목</th>
                <th class="writer">이름</th>
                <th class="regdate">등록일</th>
                <th class="viewcnt">조회수</th>
            </tr>
            <c:forEach var = "board" items="${boards}">
                <tr>
                    <td class="no">${board.bno}</td>
                    <td><a href="/ch2/board/${board.bno}">${board.title}</a></td>
                    <td>${board.writer}</td>
                    <td><fmt:formatDate value="${board.reg_date}" pattern="yyyy-MM-dd"/></td>
                    <td class="viewcnt">${board.view_cnt}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <div class="paging-container">
            <c:forEach var="i" begin="1" end="${total / pageSize + (total % pageSize > 0 ? 1 : 0)}">
                <c:choose>
                    <c:when test="${i == page}"><span class="paging-active">${i}</span></c:when>
                    <c:otherwise><a href="<c:url value='/board/list?page=${i}'/>" class="page">${i}</a></c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <br>
        <button id="writeBtn" class="btn-write" onclick="location.href='/ch2/board/write'"><i class="fa fa-pencil"></i> 글쓰기</button>
    </div>
</div>
</body>
</html>