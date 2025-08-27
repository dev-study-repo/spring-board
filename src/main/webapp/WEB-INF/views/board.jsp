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
    <div class="comment-section">
        <h3>댓글</h3>
        <div id="commentList"></div>

        <c:if test="${not empty sessionScope.id}">
            <div class="comment-form">
                <textarea id="commentContent" placeholder="댓글을 입력하세요"></textarea>
                <button type="button" id="commentBtn">등록</button>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.id}">
            <p>로그인 후 댓글을 작성할 수 있습니다.</p>
        </c:if>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
    $("#removeBtn").on("click", function(){
        if(!confirm("정말 삭제하시겠습니까?")) return;
        <%--location.href = "${pageContext.request.contextPath}/board/remove/${board.bno}";--%>
        $.ajax({
            type:'POST',
            url: 'remove/${board.bno}',
            success: function(msg){
                alert(msg);
                location.href = "${pageContext.request.contextPath}/board/list";
            },
            error: function(err){ alert(err); }
        });
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
