<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/board.css'/>">
</head>
<body>
<div class="container">
    <h2>${board.title}</h2>

    <div class="meta-info">
        <div>작성자: ${board.writer}</div>
        <div>조회수: ${board.view_cnt}</div>
        <div>댓글 수: ${board.comment_cnt}</div>
        <div>등록일: <fmt:formatDate value="${board.reg_date}" pattern="yyyy-MM-dd"/></div>
        <div>수정일: <fmt:formatDate value="${board.up_date}" pattern="yyyy-MM-dd"/></div>
    </div>

    <div class="detail-row">
        <div class="detail-label">내용</div>
        <div class="detail-value">${board.content}</div>
    </div>

    <div class="btn-group">
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
        <button type="button" id="listBtn" class="btn">목록</button>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
    $("#removeBtn").on("click", function(){
        if(!confirm("정말 삭제하시겠습니까?")) return;
        location.href = "${pageContext.request.contextPath}/board/remove?bno=${board.bno}";
    });

    $("#modifyBtn").on("click", function(){
        location.href = "${pageContext.request.contextPath}/board/modify/${board.bno}";
    });

    $("#listBtn").on("click", function(){
        location.href = "${pageContext.request.contextPath}/board/list";
    });
</script>
</body>
</html>
