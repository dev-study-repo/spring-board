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
        <div class="comment-form">
            <textarea id="commentContent" placeholder="댓글을 입력하세요"></textarea>
            <button type="button" id="commentBtn">등록</button>
        </div>
        <c:if test="${empty sessionScope.id}">
            <p id="loginAlert" style="color:red; display:none;">로그인 후 댓글을 작성할 수 있습니다.</p>
        </c:if>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    const bno = "${board.bno}";
    const sessionId = "${sessionScope.id}";

    // 댓글 불러오기
    function loadComments() {
        $.ajax({
            type: 'GET',
            url: contextPath+`/comment/${bno}`,
            dataType: 'json',
            success: function(comments) {
                const $list = $("#commentList");
                $list.empty();
                if (comments.length === 0) {
                    $list.append("<p>댓글이 없습니다.</p>");
                    return;
                }
                comments.forEach(c => {
                    const html = `
                        <div class="comment">
                            <strong>`+c.commenter+`</strong>
                            <small>`+c.reg_date+`</small>
                            <p>`+c.comment+`</p>
                        </div>`
                    ;
                    $list.append(html);
                });
            },
            error: function() {
                alert("댓글을 불러오는 중 오류가 발생했습니다.");
            }
        });
    }

    // 페이지 로딩 시 댓글 불러오기
    $(document).ready(function() {
        loadComments();
    });

    $("#removeBtn").on("click", function(){
        if(!confirm("정말 삭제하시겠습니까?")) return;
        <%--location.href = "${pageContext.request.contextPath}/board/remove/${board.bno}";--%>
        $.ajax({
            type:'POST',
            url: 'remove/${board.bno}',
            success: function(msg){
                alert(msg);
                location.href = contextPath+"/board/list";
            },
            error: function(err){ alert(err); }
        });
    });

    $("#modifyBtn").on("click", function(){
        location.href = contextPath+"/board/modify/${board.bno}";
    });

    $("#listBtn").on("click", function(){
        location.href = contextPath+"/board/list";
    });
    $("#commentBtn").on("click", function() {
        const sessionId = "${sessionScope.id}";
        const content = $("#commentContent").val().trim();

        if (!content) {
            alert("댓글 내용을 입력해주세요.");
            return;
        }

        if (!sessionId) {
            alert("로그인이 필요합니다.");
            return;
        }

        const data = {
            bno: "${board.bno}",
            comment: content,
            commenter: sessionId
        };
    });

</script>
</body>
</html>
